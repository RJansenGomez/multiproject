package com.rjansen.sdk.mapper;

public interface InboundMapper<ENTITY,INBOUND> {
    ENTITY mapInboundToDomain(INBOUND entry);
}
