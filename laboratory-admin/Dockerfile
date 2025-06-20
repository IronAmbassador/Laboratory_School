# 使用nginx作为基础镜像
FROM nginx

# 作者信息
LABEL authors="lucky"

# 将本地的静态文件复制到nginx默认的静态文件目录
COPY ./dist /usr/share/nginx/html

# 暴露端口（可选）
EXPOSE 80

# 启动nginx服务
CMD ["nginx", "-g", "daemon off;"]
