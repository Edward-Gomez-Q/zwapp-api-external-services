package com.zwap.api.proxy_service.bl;

import com.zwap.api.proxy_service.api.exception.common.NotFoundException;
import com.zwap.api.proxy_service.api.exception.common.UnprocessableEntityException;
import com.zwap.api.proxy_service.dto.EnlaceDto;
import com.zwap.api.proxy_service.dto.EnlacePersonalizadoDto;
import com.zwap.api.proxy_service.dto.EnlacePersonalizadoMetadataDto;
import com.zwap.api.proxy_service.dto.EnlacePersonalizadoResponseDto;
import com.zwap.api.proxy_service.model.EmpresaModel;
import com.zwap.api.proxy_service.model.LlaveModel;
import com.zwap.api.proxy_service.repository.EmpresaRepository;
import com.zwap.api.proxy_service.repository.LinkPagoRepository;
import com.zwap.api.proxy_service.repository.LlaveRepository;
import com.zwap.api.proxy_service.repository.ZwappUserRepository;
import org.springframework.stereotype.Service;

@Service
public class EnlaceBl {

    private final LlaveRepository llaveRepository;
    private final EmpresaRepository empresaRepository;
    private final LinkPagoRepository linkPagoRepository;
    private final ZwappUserRepository zwappUserRepository;

    public EnlaceBl(LlaveRepository llaveRepository, EmpresaRepository empresaRepository, LinkPagoRepository linkPagoRepository, ZwappUserRepository zwappUserRepository) {
        this.llaveRepository = llaveRepository;
        this.empresaRepository = empresaRepository;
        this.linkPagoRepository = linkPagoRepository;
        this.zwappUserRepository = zwappUserRepository;
    }

    public EnlaceDto getEnlacePermanente(String token) {
        validarInputParameters(token);
        token = token.replace("Bearer ", "");
        LlaveModel llaveModel = getIdCompanyOrThrow(token);
        EmpresaModel empresaModel = getCompanyOrThrow(llaveModel.getEmpresa());
        return new EnlaceDto(empresaModel.getNombre(),empresaModel.getLinkPermanentePago());
    }
    private void validarInputParameters(String token) {
        if (token == null || token.isEmpty()) {
            throw new UnprocessableEntityException("El token no puede ser nulo o vacío");
        }
    }
    private LlaveModel getIdCompanyOrThrow(String token) {
        return llaveRepository.findByLlave(token)
                .orElseThrow(() -> new UnprocessableEntityException("El token no es válido"));
    }
    private EmpresaModel getCompanyOrThrow(String companyId) {
        return empresaRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Compañia no encontrada con el ID: " + companyId));
    }

    public EnlacePersonalizadoResponseDto getEnlacePersonalizado(String token, EnlacePersonalizadoDto enlacePersonalizadoDto) {
        validarInputParameters(token);
        if (enlacePersonalizadoDto.getItems().isEmpty()) {
            throw new UnprocessableEntityException("El enlace personalizado debe tener al menos un item");
        }
        token = token.replace("Bearer ", "");
        LlaveModel llaveModel = getIdCompanyOrThrow(token);
        EmpresaModel empresaModel = getCompanyOrThrow(llaveModel.getEmpresa());
        enlacePersonalizadoDto.setTokenGateway(llaveModel.getId());
        enlacePersonalizadoDto.setUserId(zwappUserRepository.getZwappuserByIdEmpresa(empresaModel.getId())
                .orElseThrow(() -> new NotFoundException("No se pudo obtener el usuario más antiguo")).getId());
        enlacePersonalizadoDto.setMetadata(new EnlacePersonalizadoMetadataDto(empresaModel.getNombre(), empresaModel.getPrincipalContactoCorreo(), empresaModel.getId(),empresaModel.getPrincipalContactoNumero() ));
        return linkPagoRepository.createEnlacePersonalizado(enlacePersonalizadoDto)
                .orElseThrow(() -> new NotFoundException("No se pudo crear el enlace personalizado"));
    }
}
