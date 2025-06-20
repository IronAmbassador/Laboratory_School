# 使用 MySQL 做为基础镜像
FROM mysql:8

# 作者信息
LABEL authors="lucky"

# 设置时区
ENV TZ=Asia/Shanghai

# 将本地 laboratorydb-init.sql 复制到容器中
COPY ./laboratorydb-init.sql /docker-entrypoint-initdb.d/init.sql

# 暴露端口
EXPOSE 3306
