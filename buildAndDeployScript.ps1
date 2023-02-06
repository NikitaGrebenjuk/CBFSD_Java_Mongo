#create War-File using maven package
mvn package

# stopp the docker container if it exists
docker kill tcMongo

# delete docker contaienr if it exists
docker rm tcMongo

#build a docker container using the docker file in the same directory
docker build -t tc .

#Start tomcat docker container on the defined port 8888 and opening 27017 for MongoDB
docker run --name tcMongo -p 8889:8080 -p 27017:27017 -d tc sh -c "catalina.sh run"
Start-Process "http://localhost:8889/CBFSD_Java_MongoDB/"