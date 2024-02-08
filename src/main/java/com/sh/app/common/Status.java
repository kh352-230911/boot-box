package com.sh.app.common;


/**
 * 0206
 * status [예약 상태]
 * yet : 공백
 * pending: 진행중
 * confirm: 완료
 *
 * 절대적인 기준이나 절차가 아니라 불필요하다면 yet상태를 삭제해도 됩니다.
 * 현재는 시작/진행/완료 구분을 하기 위해 임시로 추가함.
 *
 * 0207
 * status 상태값을 reservation과 orderPay 테이블에서 사용하기 때문에
 * common package를 생성하여 분리함.(공용 enum class)
 */
public enum Status {
    PENDING,CONFIRM;
}
