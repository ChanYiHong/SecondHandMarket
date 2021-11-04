# 중고 물품 거래 웹 사이트

## 사용 기술 : JAVA, Spring Framework, JPA, Thymeleaf </br>
Spring Data JPA, Querydsl, HTML5, CSS, Javascript, Ajax, Socket, GitHub

### 개발 기간 : 2021.05 ~ 2021.06 / 2021.11 ~ (리펙토링 예정) 

중고나라와 당근마켓을 모티브로 판매하고자 하는 중고물품을 올리고 구매하고자 하는 중고물품을 구매하는 사이트입니다. </br>
구매자가 구매를 원하면 원하는 가격과 함께 구매 신청을 하게 됩니다. </br>
판매자는 원하는 가격과 조건을 보고 협상과 거절 옵션중 하나를 선택하게 됩니다. </br>
중고 물품 리스트에서는 해당 중고 물품의 정보 및 댓글 등을 확인할 수 있고, 좋아요 버튼기능도 구현했습니다. </br>

- Socket Programming을 통해 자유 채팅방 구현
- 판매자와 구매자의 채팅방은 배포 이후 정확한 테스트가 필요한 상황
- Ajax 기술을 활용해 restAPI 구현 (좋아요, 댓글)
- Querydsl을 통해 좋아요 많은 중고물품 띄우기 (동적 쿼리)
- Controller, Service, Repository, DTO, Domain 패키지로 나누어 유지보수가 쉽게 설계

<img width="628" alt="스크린샷 2021-10-17 오전 2 01 54" src="https://user-images.githubusercontent.com/33217033/137596017-c7554c46-37bb-4d3d-815c-88937cd36b00.png">


