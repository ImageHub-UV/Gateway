# Gateway for Image Hub

## Instructions

1. Clone the repository

```bash
git clone https://github.com/ImageHub-UV/Gateway/tree/develop
```

> If you do any change in the code, you have to update the image in the docker hub. (You need to be a member of the ImageHub-UV organization)

```
docker build -t gateway . -f ./Dockerfile  
docker tag gateway carloshot007/msvc-imagehub-gateway:latest
docker push carloshot007/msvc-imagehub-gateway:latest
```

2. Apply the changes of the deployment and service in the kubernetes cluster

```bash
kubectl apply -f ./gateway-config.yaml    
```

3. Check the url of the service

```bash
minikube service msvc-gateway --url
```

4. Test the service

Go to an application like Postman and test the service with the url obtained in the previous step. Remember see the `application.yaml` file to know the endpoints of the service.