# Fibonacci Sequence Generator

This is a Fibonacci sequence generator web application built using React for the frontend and Dropwizard for the backend.

## Description

The program provides a REST web service that takes a numerical value between 1 and 100 as input and returns the Fibonacci sequence along with a sorted sequence based on the following conditions:
- Even numbers first, in descending order
- Odd numbers, in descending order

## Getting Started

### Prerequisites

Make sure you have the following installed on your machine:
- Docker
- Docker Compose

### Installation and Usage

#### Deployment on Local Machine

1. Clone this repository to your local machine.

2. Navigate to the project directory.

3. Build and run the application using Docker Compose with the following command: docker-compose up --build

4. Access the application in your web browser at `http://localhost:3000`.

#### Deployment on Cloud

1. This application can also be deployed on the cloud using Docker Compose. Follow the steps below to deploy the application and access it on the cloud.

2. Clone this repository to your local machine.

3. Navigate to the project directory.

4. Build and deploy the application on the cloud using Docker Compose. Make sure you have a cloud provider set up and configured, I have used aws ec2 for this.

5. Once the deployment is complete, you can access the application. I have deployed mine using the following link: http://18.142.55.20:3000/.


## Technologies Used

- React: JavaScript library for building user interfaces
- Dropwizard: Java framework for developing RESTful web services
- Docker: Containerization platform for deploying applications

