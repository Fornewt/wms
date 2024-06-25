package org.example.back.domain;
import lombok.Data;
import java.util.List;

@Data
public class OutboundReq {
    private WmsOutbound wmsOutbound;
    private List<WmsOutboundDetail> wmsOutboundDetailList;
}
