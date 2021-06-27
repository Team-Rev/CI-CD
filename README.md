
## 프로젝트 소개
+ GitHub 링크: https://github.com/Team-Rev/MSA-SERVER


## 설계 구조
+ AWS EC2: CI 서버
+ Google Cloud Platform VM: CD 서버
+ GitHub: 코드 push
+ Jenkins: 자동 Build 및 Test
+ Docker: 서비스 실행
  + Docker Hub: Docker Image Push 및 Pull


## CI
+ AWS EC2(Cloud Server) 에서 구동 중
+ Jenkins: GitHub에서 발생한 hook을 감지해 새로 push한 코드를 가져와 자동 Build 및 Test 진행
  + Build 실패 시 작성해둔 개발자에게 결과 이메일 발송
  + Jenkins의 Excute Shell에서 EC2의 로컬에 있는 Shell script를 실행 해 Docker Image 작성 후 Docker Hub에 push
  + CI 서버에서 CD 서버의 Shell script 실행

### Shell Script
```
echo "[COPY JAR]"
cp /var/lib/jenkins/workspace/one-pass/one-pass/build/libs/*.jar /home/centos/jenkins-build/one-pass

echo "[REMOVE OLD IMAGE]"
docker rmi eunsilson/one-pass:latest

echo "[BUILD IMAGE]"
docker build --tag eunsilson/one-pass:latest .

echo "[DOCKER PUSH]"
docker login -u eunsilson -p Flower1401!
docker push eunsilson/one-pass:latest

echo "[SSH SCRIPTS START]"
ssh root@34.64.73.179 sh /home/eunsil1023/jenkins-scripts/scripts.sh
```


## CD
+ Google Cloud Platform(Cloud Server) 에서 구동 중
+ Docker Image로 서비스 배포

### Shell Script
```
echo "[STOP OLD CONTAINER]"
docker stop one-pass

echo "[REMOVE CONTAINER]"
docker rm one-pass

echo "[REMOVE IMAGE]"
docker rmi eunsilson/one-pass:latest

echo "[DOCKER PULL]"
docker pull eunsilson/one-pass:latest

echo "[DOCKER RUN]"
docker run -d --name one-pass -p 1111:1111 eunsilson/one-pass:latest
```


## Database: AWS RDS (MariaDB)
