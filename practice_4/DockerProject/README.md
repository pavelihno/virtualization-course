docker build -t docker_project .

docker run -it --network="bridge" -p 8080:8080 --name docker_project docker_project 

rm -f /run/rsyslogd.pid && rsyslogd -n -f /etc/rsyslog.d/10-postgresql.conf