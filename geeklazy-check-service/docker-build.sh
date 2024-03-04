GIT_SOURCE=${1}
GIT_BRANCH=${2}
DOCKER_IMAGE=${3}

baseDir="$(pwd)"
buildDir="check-build-tmp"

mkdir -p "${baseDir}/${buildDir}"

cd "${baseDir}/${buildDir}"

git clone -b ${GIT_SOURCE} ${GIT_BRANCH}

cd "geeklazy-check/"

mvn "-Dmaven.test.skip=true" clean package
if [ "$?" != "0" ]; then
  echo fail
  exit 1
fi

docker build -t "${DOCKER_IMAGE}" ${baseDir}/${buildDir}/geeklazy-check/