CREATE SCHEMA IF NOT EXISTS `kanban` DEFAULT CHARACTER SET utf8 ;
use `kanban`;

CREATE TABLE IF NOT EXISTS `kanban`.`case` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL COMMENT '需求名称',
  `chandaoId` INT NULL COMMENT '禅道id',
  `desc` VARCHAR(500) NULL,
  `type` BIGINT NULL COMMENT '产品需求 技术故事 线上bug 插入需求  1-4',
  `stage` BIGINT NULL COMMENT '需求池   开发池  开发中  待测试  测试中  待发布   上线  1-7',
  `level` BIGINT NULL COMMENT '低 中 高     1   4    7 ',
  `estimateTime` FLOAT NULL COMMENT '单位：天  比如 ：  0.5d',
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` DATETIME NULL,
  `proposeAt` DATETIME NULL COMMENT '需求提出时间',
  `transferExpectAt` DATETIME NULL COMMENT '需求移交预计时间',
  `transferReallyAt` DATETIME NULL COMMENT '需求移交实际时间',
  `codeAt` DATETIME NULL COMMENT '开发时间',
  `codeEndExpectAt` DATETIME NULL COMMENT '开发完成预计时间',
  `codeEndReallyAt` DATETIME NULL COMMENT '开发完成实际时间',
  `qaAt` DATETIME NULL COMMENT 'qa时间',
  `qaEndExpectAt` DATETIME NULL COMMENT 'qa完成预计时间',
  `qaEndReallyAt` DATETIME NULL COMMENT 'qa完成实际时间',
  `onlineExpectAt` DATETIME NULL COMMENT '上线预期时间',
  `onlineReallyAt` DATETIME NULL COMMENT '上线实际时间',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '需求';

CREATE TABLE IF NOT EXISTS `kanban`.`dict` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL COMMENT '名称',
  `ename` VARCHAR(50) NULL COMMENT '英文名称',
  `desc` VARCHAR(500) NULL COMMENT '描述',
  `code` BIGINT NULL,
  `parentCode` BIGINT NULL COMMENT '所有一级父类为0   0生万物',
  `key` VARCHAR(50) NULL,
  `value` VARCHAR(50) NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
ENGINE = InnoDB
COMMENT = '字典';

CREATE TABLE IF NOT EXISTS `kanban`.`function` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `desc` VARCHAR(500) NULL,
  `estimateTime` FLOAT NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '需求功能分解';

CREATE TABLE IF NOT EXISTS `kanban`.`meeting` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '会议',
  `name` VARCHAR(50) NULL,
  `type` BIGINT NULL COMMENT '需求移交    早会    周会   技术大会  ',
  `startAt` DATETIME NULL,
  `endAt` DATETIME NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '会议讨论';

CREATE TABLE IF NOT EXISTS `kanban`.`member` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '成员',
  `userId` INT NULL,
  `name` VARCHAR(50) NULL,
  `email` VARCHAR(100) NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '成员';

CREATE TABLE IF NOT EXISTS `kanban`.`relationCaseFunction` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '需求分解功能关系表',
  `caseId` INT NULL,
  `functionId` INT NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '需求拆分的功能关系';

CREATE TABLE IF NOT EXISTS `kanban`.`relationCaseLabel` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '需求标签关系',
  `caseId` INT NULL,
  `labelCode` BIGINT NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '需求的标签';

CREATE TABLE IF NOT EXISTS `kanban`.`relationCaseMeeting` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '需求会议关系',
  `caseId` INT NULL,
  `meetingId` INT NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '需求的会议';

CREATE TABLE IF NOT EXISTS `kanban`.`relationCaseMember` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `roleCode` BIGINT NULL COMMENT '角色代码\n',
  `memberId` INT NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '需求参与的各个成员';

CREATE TABLE IF NOT EXISTS `kanban`.`relationMeetingMember` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '会议成员关系',
  `meetingId` INT NULL,
  `memberId` INT NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '会议成员';

CREATE TABLE IF NOT EXISTS `kanban`.`relationTeamCase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `teamId` INT NULL,
  `caseId` INT NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '团队的需求';

CREATE TABLE IF NOT EXISTS `kanban`.`relationTeamMember` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '团队和成员关系\n',
  `teamId` INT NULL,
  `memberId` INT NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '团队成员';

CREATE TABLE IF NOT EXISTS `kanban`.`team` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `title` VARCHAR(100) NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '团队';

CREATE TABLE IF NOT EXISTS `kanban`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL,
  `password` VARCHAR(128) NULL,
  `ctime` DATETIME NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '用户表';

CREATE TABLE IF NOT EXISTS `kanban`.`relationMemberRole` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `memberId` INT NULL,
  `roleCode` BIGINT NULL,
  `cm` INT NULL,
  `ctime` DATETIME NULL,
  `um` INT NULL,
  `utime` DATETIME NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;



INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '角色', 'role', '产品经理（Product Manager）是企业中专门负责产品管理的职位，产品经理负责调查并根据用户的需求，确定开发何种产品，选择何种技术、商业模式等。并推动相应产品的开发组织，他还要根据产品的生命周期，协调研发、营销、运营等，确定和组织实施相应的产品策略，以及其他一系列相关的产品管理活动。', 1000010000 , 10000, 'pm', '产品经理', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '角色', 'role', '什么是攻城狮', 1000010086 , 10000, 'se', '超级攻城狮', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '角色', 'role', '什么是测试', 1000010010 , 10000, 'qa', '质量评测师', now(), 0);


INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '需求类型', 'caseType', '什么', 1001010001 , 10010, '1', '产品需求', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '需求类型', 'caseType', '什么', 1001010002 , 10010, '2', '技术故事', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '需求类型', 'caseType', '什么', 1001010003 , 10010, '3', '线上bug', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '需求类型', 'caseType', '什么', 1001010004 , 10010, '4', '插入需求', now(), 0);

INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '流程阶段', 'caseStage', '什么', 1002010001 , 10020, '1', '需求池', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '流程阶段', 'caseStage', '什么', 1002010002 , 10020, '2', '开发池', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '流程阶段', 'caseStage', '什么', 1002010003 , 10020, '3', '开发中', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '流程阶段', 'caseStage', '什么', 1002010004 , 10020, '4', '待测试', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '流程阶段', 'caseStage', '什么', 1002010005 , 10020, '5', '测试中', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '流程阶段', 'caseStage', '什么', 1002010006 , 10020, '6', '待发布', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '流程阶段', 'caseStage', '什么', 1002010007 , 10020, '7', '上线', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '流程阶段', 'caseStage', '什么', 1002010008 , 10020, '8', '存档', now(), 0);


INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '需求优先级', 'caseLevel', '什么', 1003010001 , 10030, '1', '低', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '需求优先级', 'caseLevel', '什么', 1003010004 , 10030, '4', '中', now(), 0);
INSERT INTO `kanban`.`dict` (`name`, `ename`, `desc`, `code`, `parentCode`, `key`, `value`, `ctime`, `status`)  VALUES ( '需求优先级', 'caseLevel', '什么', 1003010007 , 10030, '7', '高', now(), 0);


-- INSERT INTO `kanban`.`user` (`id`,`username`, `password`, `ctime`, `status`) VALUES ('1','root', '63a9f0ea7bb98050796b649e85481845', now(), 0);
-- INSERT INTO `kanban`.`member` (`id`, `userId`, `name`, `email`, `cm`, `ctime`, `status`) VALUES (1, 1, '欧阳爽', '88196@sh.lianjia.com', 1, now(), 0);
-- INSERT INTO `kanban`.`relationMemberRole` (`id`, `memberId`, `roleCode`, `cm`, `ctime`, `status`) VALUES (1, 1, 1000010086, 1, now(), 0);




