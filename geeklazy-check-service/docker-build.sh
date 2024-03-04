if [ $# -lt 3 ]; then
  echo fail
  exit 1
fi

GIT_SOURCE=${1}
GIT_BRANCH=${2}
DOCKER_IMAGE=${3}

baseDir="$(pwd)"
buildDir="check-build-tmp"

echo "mkdir -p ${baseDir}/${buildDir}"
mkdir -p "${baseDir}/${buildDir}"

echo "cd ${baseDir}/${buildDir}"
cd "${baseDir}/${buildDir}"

echo "git clone -b ${GIT_BRANCH} ${GIT_SOURCE}"
git clone -b ${GIT_BRANCH} ${GIT_SOURCE}

echo "cd geeklazy-check/"
cd "geeklazy-check/"

echo "mvn -Dmaven.test.skip=true clean package"
mvn "-Dmaven.test.skip=true" clean package
if [ "$?" != "0" ]; then
  echo fail
  exit 1
fi

echo "docker build -t "${DOCKER_IMAGE}" ${baseDir}/${buildDir}/geeklazy-check/geeklazy-check-service/"
docker build -t "${DOCKER_IMAGE}" ${baseDir}/${buildDir}/geeklazy-check/geeklazy-check-service/

cd ${baseDir}
cp ${baseDir}/${buildDir}/geeklazy-check/geeklazy-check-service/docker-run.sh ${baseDir}/check-docker-run.sh
chmod +x check-docker-run.sh
rm -rf ${baseDir}/${buildDir}