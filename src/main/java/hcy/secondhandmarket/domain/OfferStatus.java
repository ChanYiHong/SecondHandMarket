package hcy.secondhandmarket.domain;

public enum OfferStatus {

    /**
     * WAIT : 판매자의 제안 기다리는 상태
     * DENY : 판매자가 제안, 협상을 거절한 상태
     * NEGO : 판매자가 제안을 받아 협상중인 상태
     * ACCEPT : 판매자와 구매자가 협상 성공해서 서로 거래 대기중인 상태
     * SELLED : 거래가 끝나서 판매완료된 상태
     * **/
    WAIT, DENY, NEGO, ACCEPT, SELLED

}
