SPECTRUM_HOST=${1}
SPECTRUM_KEY=${2}
SPECTRUM_SECRET=${3}
DOCKER_IMAGE=${4}

docker rm -f geeklazy-spectrum
docker run -d --name geeklazy-check --network geeklazy-net -e SPECTRUM_HOST=${SPECTRUM_HOST} -e SPECTRUM_KEY=${SPECTRUM_KEY} -e SPECTRUM_SECRET=${SPECTRUM_SECRET} geeklazy-check:1.0-SNAPSHOT
