pipeline {
  agent {
    docker {
      image 'openjdk:8-alpine'
      args '-v /tmp:/tmp'
      reuseNode true
    }

  }
  stages {
    stage('Build') {
      steps {
        echo 'Build Kohmon'
        sh './gradlew clean build -x test'
        echo 'Publish Local Maven'
        sh './gradlew publishToMavenLocal -x test -x signMavenJavaPublication'
      }
    }

  }
  post {
        always {
            archiveArtifacts artifacts: '?/.m2/repository/co/mtarget/sekidul/**/*.jar', fingerprint: true
            archiveArtifacts artifacts: '?/.m2/repository/co/mtarget/sekidul/**/*.pom', fingerprint: true
            archiveArtifacts artifacts: '?/.m2/repository/co/mtarget/sekidul/**/*.module', fingerprint: true
        }
    }
