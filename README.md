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


## 2. DeadLock 일으키기
#### 1) 쿠폰 발행 메서드
쿠폰발행 메서드에서는

1. coupon 테이블의 잔여쿠폰 개수 -1
2. coupon_history 테이블에서 발행된 쿠폰 개수 +1

위 순서대로 실행한다.

#### 2) 쿠폰 회수 메서드
쿠폰 회수 메서드에서는

1. coupon_history 테이블에서 발행된 쿠폰 개수 -1
2. coupon 테이블의 잔여쿠폰 개수 +1

위 순서대로 실행한다.

<br>
<hr>

## 3. DeadLock 해결해보기

#### * 접근하는 테이블 순서를 신경쓰기
쿠폰 회수 메서드와 쿠폰 발행 메서드에서 업데이트하는 테이블의 순서를 같게 만들어주기만 해도 해결된다.









