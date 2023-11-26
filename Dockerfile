FROM eclipse-temurin:17 AS builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} web-scrapping-kata-0.0.1.jar
RUN java -Djarmode=layertools -jar web-scrapping-kata-0.0.1.jar extract

FROM eclipse-temurin:17-focal
LABEL authors="github.com/ehayik"

# Install necessary packages
RUN apt-get update && \
    apt-get install -y wget curl unzip firefox

# Download and install geckodriver
RUN GECKODRIVER_VERSION=`curl -Ls -o /dev/null -w %{url_effective} https://github.com/mozilla/geckodriver/releases/latest | sed -n -r 's/.*\/v(.*)/\1/p' | tr -d '\n'` && \
    wget --no-verbose -O /tmp/geckodriver.tar.gz https://github.com/mozilla/geckodriver/releases/download/v"$GECKODRIVER_VERSION"/geckodriver-v"$GECKODRIVER_VERSION"-linux64.tar.gz && \
    tar -C /opt -zxf /tmp/geckodriver.tar.gz && \
    rm /tmp/geckodriver.tar.gz && \
    mv /opt/geckodriver /opt/geckodriver-"$GECKODRIVER_VERSION" && \
    chmod 755 /opt/geckodriver-"$GECKODRIVER_VERSION" && \
    ln -fs /opt/geckodriver-"$GECKODRIVER_VERSION"/usr/bin/geckodriver

## Clean up
RUN apt autoremove -y && apt autoclean -y && \
    rm -rf /var/lib/apt/lists/*

# # Set up the xvfb (X Virtual Framebuffer)
ENV DISPLAY=:99

COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]