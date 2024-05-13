GRADLE=gradlew
SPRINT_BOOT_APP=clustered_data_warehouse-0.0.1-SNAPSHOT.jar

build:
	$(GRADLE) build

clean:
	@$(GRADLE) clean

run:
	java -jar build/libs/$(SPRINT_BOOT_APP)