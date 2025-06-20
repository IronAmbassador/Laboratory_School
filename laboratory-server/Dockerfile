# 使用 openjdk 8 作为基础镜像(根据项目选择jdk的版本)
FROM openjdk:8

# 作者信息
LABEL authors="lucky"

# 将目录中的 jar 文件复制到容器中
COPY *.jar app.jar

# 设置活动配置文件为dev
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENV SPRING_PROFILES_ACTIVE=prod

# 暴露端口
EXPOSE 8090

# 指定容器启动后执行的命令
CMD ["java", "-jar", "app.jar"]