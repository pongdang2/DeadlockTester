# DeadlockTester

<hr>

## 1. 설명

### DB 

#### MySQL DB 사용

* 테이블
1. coupon

|Field|Type|Null|Key|Default|Extra|
|---|---|---|---|---|---|
|id|int|NO|PRI|NULL| |
|remain_count|int|YES| |NULL| |

```
create table coupon(
    id int  primary key,
    remain_count int
)ENGINE=INNODB;
```

2. coupon_history

| Field               |Type|Null|Key|Default|Extra|
|---------------------|---|---|---|---|---|
| id                  |int|NO|PRI|NULL| |
| issued_coupon_count |int|YES| |NULL| |


```
create table coupon_history(
    id int  primary key,
    issued_coupon_count int
)ENGINE=INNODB;
```
<hr>

### ThreadPool

`me.lkhz.threadpool.ThreadPool` <br>
트랜잭션을 대량으로 일으키기 위해 Singleton 패턴을 활용해 ThreadPool을 간단하게 구현했습니다.

### CouponDAO_01
실행했을 때, DeadLock이 발생하는 클래스입니다.
Main메서드의 couponDAO를 CouponDAO_01의 인스턴스로 초기화하면 DeadLock이 발생합니다.

### CouponDAO_02
실행했을 때, DeadLock이 발생하지 않는 클래스입니다.
Main메서드의 couponDAO를 CouponDAO_02의 인스턴스로 초기화하면 DeadLock이 발생하지 않습니다.

<br>

<hr>

## 2. DeadLock 일으키기
#### 1) 쿠폰 발행 메서드
쿠폰발행 메서드에서는

1. coupon 테이블의 잔여쿠폰 개수 -1
2. coupon_history 테이블에서 발행된 쿠폰 개수 +1

위 순서대로 실행합니다.

#### 2) 쿠폰 회수 메서드
쿠폰 회수 메서드에서는

1. coupon_history 테이블에서 발행된 쿠폰 개수 -1
2. coupon 테이블의 잔여쿠폰 개수 +1

위 순서대로 실행합니다.

<br>
<hr>

## 3. DeadLock이 발생하는 이유

1. 쿠폰 발행 메서드에서는 coupon 테이블을 수정하기 위해 coupon 테이블에 Lock을 겁니다.
2. 쿠폰 회수 메서드에서는 coupon_history 테이블을 수정하기 위해 coupon_history 테이블에 Lock을 겁니다.
3. 쿠폰 발행 메서드에서는 coupon을 수정한 이후, coupon_history를 수정하기위해 쿠폰회수 메서드의 종료를 기다립니다.
4. 쿠폰 회수 메서드에서는 coupon_history 테이블을 수정한 이후, coupon을 수정하기 위해 쿠폰 발행 메서드의 종료를 기다립니다.
5. 이렇게 서로 종료를 기다리고 있는 Deadlock이 발생합니다.

<br>
<hr>

## 4. DeadLock 해결해보기

#### * 접근하는 테이블 순서를 신경쓰기
쿠폰 회수 메서드와 쿠폰 발행 메서드에서 업데이트하는 테이블의 순서를 같게 만들어주기만 해도 해결됩니다.









