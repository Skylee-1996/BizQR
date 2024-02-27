package ezen.bizqr.admin.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitVO {
    private long index;
    private String visit;
    private long number;
}
