FROM ubuntu:18.04
RUN apt-get update
RUN apt-get install -y maven pandoc texlive texlive-fonts-recommended
RUN mkdir simplewebapp
COPY . simplewebapp
WORKDIR "simplewebapp"
RUN mvn package; export PORT=5000
CMD sh target/bin/simplewebapp
