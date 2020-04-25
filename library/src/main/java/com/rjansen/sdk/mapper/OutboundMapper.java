package com.rjansen.sdk.mapper;

public interface OutboundMapper<ENTITY, OUTBOUND> {
    OUTBOUND mapDomainToOutbound(ENTITY entity);
}
