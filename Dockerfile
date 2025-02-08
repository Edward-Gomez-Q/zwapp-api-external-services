FROM ubuntu:latest
LABEL authors="osval"

ENTRYPOINT ["top", "-b"]