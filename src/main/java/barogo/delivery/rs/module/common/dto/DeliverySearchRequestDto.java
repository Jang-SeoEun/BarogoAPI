package barogo.delivery.rs.module.common.dto;

import barogo.delivery.rs.module.common.utils.SecurityUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "배달조회조건" , description = "아이디, 조회시작일, 조회 종료일,페이지번호, 페이지 크기를 가진 조회조건 Dto")
public class DeliverySearchRequestDto {
    @ApiModelProperty(value = "아이디 공백일 경우 토큰의 아이디 값으로 조회",example = "SeoEun22")
    private String memberId;
    @ApiModelProperty(value = "조회 시작일",example = "2022-05-13 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromDate;
    @ApiModelProperty(value = "조회 종료일",example = "2022-05-15 23:59:59")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toDate;
    @ApiModelProperty(value = "페이지번호", example = "1")
    private int pageNo;
    @ApiModelProperty(value = "페이지크기",example = "10")
    private int pageSize;

    public String getMemberId() {
        return SecurityUtil.hasRoleUser() ? SecurityUtil.getCurrentMemberId().get() : this.memberId;
    }
}
