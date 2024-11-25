# socialapp-api

You must have Maven installed!

To build:
```
mvn clean install
```
To test only:
```
mvn test
```

To run from MacOs/Linux CLI:
```
./runme.sh
```
To host on GCP using Cloud Run:

# Assuming your repository is named "my-repo" in the "us-central1" region
# and your project ID is "my-project-id"

```
gcloud auth login
```
```
gcloud config set project  itm-web-svc
```
# Authenticate with Artifact Registry
```
gcloud auth configure-docker us-central1-docker.pkg.dev
```
# Tag your image - did not work, see important below
docker tag my-image:latest us-central1-docker.pkg.dev/my-project-id/my-repo/my-image:latest

# Push the image - did not work, see important below
docker push us-central1-docker.pkg.dev/my-project-id/my-repo/my-image:latest

us-central1-docker.pkg.dev/itm-web-svc/itm-docker-repo

IMPORTANT: 
The below disables GCP builds default gcloudignore
```
 gcloud config set gcloudignore/enabled false
```
```
 gcloud meta list-files-for-upload
```
```
gcloud builds submit --tag us-central1-docker.pkg.dev/itm-web-svc/itm-docker-repo/csc207-restfulapi:latest
```