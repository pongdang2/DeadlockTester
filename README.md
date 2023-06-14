# DeadlockTester

<hr>

## 1. DB

#### MySQL DB 사용

* 테이블
1. coupon

|Field|Type|Null|Key|Default|Extra|
|---|---|---|---|---|---|
|id|int|NO|PRI|NULL| |
|remain_count|int|YES| |NULL| |

2. coupon_history

| Field               |Type|Null|Key|Default|Extra|
|---------------------|---|---|---|---|---|
| id                  |int|NO|PRI|NULL| |
| issued_coupon_count |int|YES| |NULL| |

<hr>


## 2. DeadLock 일으키기
#### 1) 쿠폰 발행 메서드
쿠폰발행 메서드에서는

1. coupon 테이블의 잔여쿠폰 개수 -1
2. coupon_history 테이블에서 발행된 쿠폰 개수 + 1

위 순서대로 실행한다.

#### 2) 쿠폰 회수 메서드
쿠폰 회수 메서드에서는

1. coupon_history 테이블에서 발행된 쿠폰 개수 -1
2. coupon 테이블의 잔여쿠폰 개수 +1

위 순서대로 실행한다.

<br>
<br>

다만 각 메서드 중간에 스레드를 10초 재워서 deadlock을 일으켰다.





