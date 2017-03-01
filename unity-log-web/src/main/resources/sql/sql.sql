CREATE TABLE ULOG_MENU (
             men_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
             name varchar(200) NOT NULL COMMENT '菜单名称',
             men_url varchar(200) NOT NULL COMMENT '菜单url',
             parent_men_id bigint(20) DEFAULT NULL COMMENT '父菜单id',
             domain_name varchar(200) NOT NULL,
             created timestamp NULL DEFAULT NULL COMMENT '创建时间',
             modified timestamp NULL DEFAULT NULL COMMENT '修改时间',
             remark varchar(400) DEFAULT NULL COMMENT '备注',
             PRIMARY KEY (men_id)
           ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE ULOG_DOMAIN (
               name varchar(200) NOT NULL COMMENT '主键',
               created timestamp NULL DEFAULT NULL COMMENT '创建时间',
               modified timestamp NULL DEFAULT NULL COMMENT '修改时间',
               remark varchar(400) DEFAULT NULL COMMENT '备注',
               PRIMARY KEY (name)
             ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ULOG_TYPE (
             id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',         
             type_name varchar(200) NOT NULL COMMENT '类型appender name',  
             domain_name bigint(20) NOT NULL COMMENT '域名-外键',        
             created timestamp NULL DEFAULT NULL COMMENT '创建时间',     
             modified timestamp NULL DEFAULT NULL COMMENT '修改时间',    
             remark varchar(400) DEFAULT NULL COMMENT '备注',              
             PRIMARY KEY (id),                                               
             KEY ulog_type_domain_name (domain_name)                       
           ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ULOG_IP (
           id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',       
           ip varchar(200) NOT NULL COMMENT '服务器ip',               
           domain_name bigint(20) NOT NULL COMMENT '域名-外键',      
           created timestamp NULL DEFAULT NULL COMMENT '创建时间',   
           modified timestamp NULL DEFAULT NULL COMMENT '修改时间',  
           remark varchar(400) DEFAULT NULL COMMENT '备注',            
           PRIMARY KEY (id),                                             
           KEY ulog_ip_domain_name (domain_name)                       
         ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


