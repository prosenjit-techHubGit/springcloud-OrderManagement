pipeline {
  agent {
    docker {
      args '-v /root/.m2:/root/.m2'
      image 'maven:3-alpine'
    }

  }
  stages {
    stage('Build') {
      steps {
        dir(path: 'account-service') {
          sh 'mvn -B -DskipTests clean package'
        }

      }
    }
    stage('Test') {
      steps {
        dir(path: 'account-service') {
          sh 'mvn test'
        }

      }
    }
    stage('Deliver') {
      steps {
        dir(path: 'account-service') {
          sh './jenkins/scripts/deliver.sh'
        }

      }
    }
  }
}