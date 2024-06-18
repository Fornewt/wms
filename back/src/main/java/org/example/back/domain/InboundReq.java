package org.example.back.domain;
import lombok.Data;

import java.util.List;

// 我需要定义一个复合对象来接收：一条入库单和多条入库明细
@Data
public class InboundReq {
    private WmsInbound wmsInbound;
    private List<WmsInboundDetail> wmsInboundDetailList;
}
