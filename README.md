# 채용 관리 시스템

## 개발 목표

---
전에 구현했던 원티드 프리 온보딩 사전 과제를 다시 한번 구현해 보면서 요구 사항을 추가해 고도화 할 예정이다.

## 기술 스택

---
- SpringBoot
- Spring Data JPA
- QueryDsl
- Mysql

## DB 설계

---
<img width="700" alt="Screenshot 2023-11-07 at 19 40 11" src="https://github.com/HEUKWU/wanted-pre-onboarding-backend/assets/100930333/a7a7d548-d6e5-47a3-8e70-0501c92aa545">

## 기본 기능

---
### 1. 채용 공고 등록
- 회사는 채용 포지션, 채용 내용 기술 스택이 포함된 채용 공고를 등록할 수 있다.
### 2. 채용 공고 수정
- 회사는 공고 내용을 수정할 수 있다.
### 3. 채용 공고 삭제
- 회사는 등록한 채용 공고를 삭제할 수 있다.
### 4. 채용 공고 목록 조회
- 사용자는 회사 정보와 공고 정보가 나열된 채용 공고 목록을 조회할 수 있다.
- 사용자는 회사 정보와 공고 정보 기반의 검색어로 특정 채용 공고를 검색 조회할 수 있다.
### 5. 채용 공고 상세 페이지 조회
- 사용자는 상세 설명과 해당 공고를 올린 회사의 다른 채용 공고 목록을 포함한 채용 상세 페이지를 조회할 수 있다.
### 6. 채용 지원
- 사용자는 채용 공고별 1회 한정 지원할 수 있다.