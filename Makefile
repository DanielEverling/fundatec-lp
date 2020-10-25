MAVEN_CMD			:= mvn
PROJECT_NAME		:= fundatec-lp

.DEFAULT_GOAL := help

install: ## Executes maven clean install
	@$(MAVEN_CMD) clean install

local: ## Executes maven clean install
	cd apis && $(MAVEN_CMD) quarkus:dev -Ddebug

.PHONY: build
build: ## Builds docker images
	@docker-compose build

up: ## Starts applications and dependencies using docker
	@docker-compose up -d $(PROJECT_NAME)

logs: ## Shows applications and dependencies logs
	@docker-compose --project-name $(PROJECT_NAME) logs -f

down: ## Shuts Down applications and dependencies
	@docker-compose --project-name $(PROJECT_NAME) down || true
	@docker-compose --project-name $(PROJECT_NAME) kill || true
	@docker-compose --project-name $(PROJECT_NAME) rm -f || true

stop: ## Stops all containers
	@docker-compose stop -t 0

db: ## Starts database container
	@docker-compose up -d db

rebuild-and-up: ## Rebuilds and starts the entire application
	down fast-install up

.PHONY: help
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
