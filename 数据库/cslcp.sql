/*
Navicat MySQL Data Transfer

Source Server         : aaaaa
Source Server Version : 80023
Source Host           : 172.16.122.79:3306
Source Database       : cslcp

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2021-06-01 14:02:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` char(100) DEFAULT NULL,
  `password` char(100) DEFAULT NULL,
  `accountId` int DEFAULT NULL,
  `name` char(50) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `idCardNo` char(50) DEFAULT NULL,
  `hiredDate` date DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createUserId` int DEFAULT NULL,
  `createUserName` char(50) DEFAULT NULL,
  `modifyDate` date DEFAULT NULL,
  `modifyUserId` int DEFAULT NULL,
  `modifyUserName` char(50) DEFAULT NULL,
  `isDel` tinyint DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_3` (`accountId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of account
-- ----------------------------

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `pid` int DEFAULT NULL COMMENT '父项主键',
  `name` char(100) DEFAULT NULL COMMENT '名称',
  `lng` float(255,20) DEFAULT NULL COMMENT '经度',
  `lat` float(255,20) DEFAULT NULL COMMENT '维度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=820002 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='国家省份与城市表';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', null, '中国', null, null);
INSERT INTO `address` VALUES ('2', null, '美国', null, null);
INSERT INTO `address` VALUES ('3', null, '俄罗斯', null, null);
INSERT INTO `address` VALUES ('4', '1', '浙江省', null, null);
INSERT INTO `address` VALUES ('5', '4', '宁波市', '121.62739562988281000000', '29.86732292175293000000');
INSERT INTO `address` VALUES ('6', '1', '河北省', null, null);
INSERT INTO `address` VALUES ('7', '6', '唐山市', '118.18991088867188000000', '39.63814163208008000000');
INSERT INTO `address` VALUES ('8', '1', '广东省', null, null);
INSERT INTO `address` VALUES ('9', '8', '广州市', '113.27200317382812000000', '23.13560295104980500000');
INSERT INTO `address` VALUES ('10', '1', '山东省', null, null);
INSERT INTO `address` VALUES ('11', '10', '青岛市', '120.38888549804688000000', '36.07292556762695000000');
INSERT INTO `address` VALUES ('12', '1', '江苏省', null, null);
INSERT INTO `address` VALUES ('13', '12', '苏州市', '120.59183502197266000000', '31.30331802368164000000');
INSERT INTO `address` VALUES ('14', '10', '日照市', '119.52996826171875000000', '35.42260360717773400000');
INSERT INTO `address` VALUES ('15', '1', '江西省', null, null);
INSERT INTO `address` VALUES ('16', '15', '烟台市', '121.45613861083984000000', '37.46843719482422000000');
INSERT INTO `address` VALUES ('17', '12', '镇江市', '119.42819213867188000000', '32.19398498535156000000');
INSERT INTO `address` VALUES ('18', '1', '辽宁省', null, null);
INSERT INTO `address` VALUES ('19', '18', '大连市', '121.62162780761719000000', '38.91962814331055000000');
INSERT INTO `address` VALUES ('20', '12', '南通市', '120.90216827392578000000', '31.98728370666504000000');
INSERT INTO `address` VALUES ('21', '1', '河南省', null, null);
INSERT INTO `address` VALUES ('22', '21', '黄骅市', '117.33589935302734000000', '38.37545776367187500000');
INSERT INTO `address` VALUES ('23', '12', '泰州市', '119.92726898193360000000', '32.46286773681640600000');
INSERT INTO `address` VALUES ('24', '1', '广西省', null, null);
INSERT INTO `address` VALUES ('25', '24', '防城港市', '108.36271667480469000000', '21.69273567199707000000');
INSERT INTO `address` VALUES ('26', '8', '深圳市', '114.06512451171875000000', '22.55085945129394500000');
INSERT INTO `address` VALUES ('27', '12', '南京市', '118.80126953125000000000', '32.06391906738281000000');
INSERT INTO `address` VALUES ('28', '1', '福建省', null, null);
INSERT INTO `address` VALUES ('29', '28', '福州市', '110.04379272460938000000', '26.42791175842285000000');
INSERT INTO `address` VALUES ('30', '12', '连云港市', '119.22574615478516000000', '34.60153579711914000000');
INSERT INTO `address` VALUES ('31', '18', '营口市', '122.22464752197266000000', '40.63092422485351600000');
INSERT INTO `address` VALUES ('32', '2', '乔治亚州', null, null);
INSERT INTO `address` VALUES ('33', '32', '亚特兰大', '-84.39685821533203000000', '33.75911712646484400000');
INSERT INTO `address` VALUES ('34', '2', '马里兰州', null, null);
INSERT INTO `address` VALUES ('35', '34', '巴尔的摩', '-76.60321807861328000000', '39.29547119140625000000');
INSERT INTO `address` VALUES ('36', '2', '宾夕法尼亚州', null, null);
INSERT INTO `address` VALUES ('37', '26', '伯明翰', '-86.81314086914062000000', '33.53002548217773400000');
INSERT INTO `address` VALUES ('38', '2', '麻萨诸塞州', null, null);
INSERT INTO `address` VALUES ('39', '38', '波士顿', '-71.05502319335938000000', '42.36017608642578000000');
INSERT INTO `address` VALUES ('40', '2', '北卡罗来纳州', null, null);
INSERT INTO `address` VALUES ('41', '40', '夏洛特', '-80.84061431884766000000', '35.23113632202148400000');
INSERT INTO `address` VALUES ('42', '2', '伊利诺伊州', null, null);
INSERT INTO `address` VALUES ('43', '42', '芝加哥', '-87.63647460937500000000', '41.89305877685547000000');
INSERT INTO `address` VALUES ('44', '2', '俄亥俄州', null, null);
INSERT INTO `address` VALUES ('45', '44', '辛辛那提', '-84.50389862060547000000', '39.11483383178711000000');
INSERT INTO `address` VALUES ('46', '44', '克里夫兰', '-81.68650054931640000000', '41.50767135620117000000');
INSERT INTO `address` VALUES ('47', '44', '哥伦布', '-84.31215667724610000000', '39.55115890502930000000');
INSERT INTO `address` VALUES ('48', '44', '达拉斯', '-96.82055664062500000000', '32.76670455932617000000');
INSERT INTO `address` VALUES ('49', '2', '科罗拉多州', null, null);
INSERT INTO `address` VALUES ('50', '49', '丹佛', '-104.98622894287110000000', '39.77052307128906000000');
INSERT INTO `address` VALUES ('51', '2', '得克萨斯州', null, null);
INSERT INTO `address` VALUES ('52', '51', '埃尔帕索', '-94.90270233154297000000', '29.38384437561035000000');
INSERT INTO `address` VALUES ('53', '2', '新泽西州', null, null);
INSERT INTO `address` VALUES ('54', '53', '伊丽莎白', '-74.26948547363281000000', '40.38851547241211000000');
INSERT INTO `address` VALUES ('55', '51', '沃思堡', '-97.32721710205078000000', '32.75344848632812500000');
INSERT INTO `address` VALUES ('56', '2', '密西西比州', null, null);
INSERT INTO `address` VALUES ('57', '56', '霍姆斯', '-90.12986755371094000000', '33.16088867187500000000');
INSERT INTO `address` VALUES ('58', '3', '阿尔汉格尔斯克州', null, null);
INSERT INTO `address` VALUES ('59', '58', '阿尔汉格尔斯克', '40.52566146850586000000', '64.54217529296875000000');
INSERT INTO `address` VALUES ('60', '3', '加里宁格勒州', null, null);
INSERT INTO `address` VALUES ('61', '60', '加里宁格勒', '20.51085472106933600000', '54.71358871459961000000');
INSERT INTO `address` VALUES ('62', '3', '奥廖尔州', null, null);
INSERT INTO `address` VALUES ('63', '62', '科尔萨科夫斯基区', '32.16465759277344000000', '67.10734558105469000000');
INSERT INTO `address` VALUES ('64', '3', '克拉斯诺达尔边疆区', null, null);
INSERT INTO `address` VALUES ('65', '64', '新罗西斯克', '37.76886749267578000000', '44.72872161865234400000');
INSERT INTO `address` VALUES ('66', '3', '列宁格勒州', null, null);
INSERT INTO `address` VALUES ('67', '66', '圣彼得堡', '30.32788658142090000000', '59.93620681762695000000');
INSERT INTO `address` VALUES ('68', '3', '摩尔曼斯克州', null, null);
INSERT INTO `address` VALUES ('69', '68', '翁巴', '31.46725082397461000000', '69.46570587158203000000');
INSERT INTO `address` VALUES ('70', '3', '滨海边疆区', null, null);
INSERT INTO `address` VALUES ('71', '70', '海参崴', '131.89761352539062000000', '43.12129211425781000000');
INSERT INTO `address` VALUES ('72', '3', '库尔斯克州', null, null);
INSERT INTO `address` VALUES ('73', '72', '库尔斯克', '34.79077148437500000000', '52.06827926635742000000');

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int DEFAULT NULL,
  `type` int DEFAULT NULL COMMENT '0',
  `showText` char(100) DEFAULT NULL,
  `url` char(100) DEFAULT NULL,
  `icon` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of authority
-- ----------------------------

-- ----------------------------
-- Table structure for authorityroleref
-- ----------------------------
DROP TABLE IF EXISTS `authorityroleref`;
CREATE TABLE `authorityroleref` (
  `id` int NOT NULL AUTO_INCREMENT,
  `authorityId` int DEFAULT NULL,
  `roleid` int DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createUserId` int DEFAULT NULL,
  `createUserName` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_27` (`authorityId`) USING BTREE,
  KEY `FK_Reference_28` (`roleid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of authorityroleref
-- ----------------------------

-- ----------------------------
-- Table structure for billgoods
-- ----------------------------
DROP TABLE IF EXISTS `billgoods`;
CREATE TABLE `billgoods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blId` int DEFAULT NULL,
  `MarksAndNos` varchar(1000) DEFAULT NULL,
  `NoAndKindOfPackages` varchar(1000) DEFAULT NULL,
  `DescriptionOfgoods` varchar(1000) DEFAULT NULL,
  `GrossWeight` varchar(1000) DEFAULT NULL,
  `Measurement` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_17` (`blId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of billgoods
-- ----------------------------

-- ----------------------------
-- Table structure for billoflading
-- ----------------------------
DROP TABLE IF EXISTS `billoflading`;
CREATE TABLE `billoflading` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blNo` varchar(100) DEFAULT NULL,
  `sendCompanyId` int DEFAULT NULL,
  `receiveCompanyId` int DEFAULT NULL,
  `signatureCompanyId` int DEFAULT NULL COMMENT 'ǩ',
  `proId` int DEFAULT NULL,
  `billStatus` tinyint DEFAULT NULL,
  `shipper` varchar(100) DEFAULT NULL,
  `consignee` varchar(100) DEFAULT NULL,
  `notifyParty` varchar(100) DEFAULT NULL,
  `placeOfreceipt` varchar(100) DEFAULT NULL,
  `vessel` varchar(100) DEFAULT NULL,
  `portOfLoading` int DEFAULT NULL,
  `portOfTransshipment` varchar(100) DEFAULT NULL,
  `PortOfDischarge` varchar(100) DEFAULT NULL,
  `PlaceOfDelivery` varchar(100) DEFAULT NULL,
  `freightCharges` varchar(100) DEFAULT NULL,
  `NoOfOriginal` varchar(100) DEFAULT NULL,
  `declared` varchar(100) DEFAULT NULL,
  `voyageNo` varchar(100) DEFAULT NULL,
  `preCarriageBy` varchar(100) DEFAULT NULL,
  `planceAndDate` varchar(100) DEFAULT NULL,
  `totalNumber` varchar(100) DEFAULT NULL,
  `finalDestination` varchar(100) DEFAULT NULL,
  `fordelivery` varchar(100) DEFAULT NULL,
  `typeOfMovement` varchar(100) DEFAULT NULL,
  `rcAuditStatus` tinyint DEFAULT '0',
  `rcfAuditResult` tinyint DEFAULT NULL COMMENT '1:ͨ',
  `rcfAuditOpinion` varchar(100) DEFAULT NULL,
  `rcfAuditUserAccount` varchar(100) DEFAULT NULL,
  `rcfAuditUserName` varchar(100) DEFAULT NULL,
  `rcfAuditUserDay` date DEFAULT NULL,
  `scAuditStatus` tinyint DEFAULT '0',
  `scfAuditResult` tinyint DEFAULT NULL COMMENT '1:ͨ',
  `scfAuditOpinion` varchar(100) DEFAULT NULL,
  `scfAuditUserAccount` varchar(100) DEFAULT NULL,
  `scfAuditUserName` varchar(100) DEFAULT NULL,
  `scfAuditUserDay` date DEFAULT NULL,
  `signatureAuditStatus` tinyint DEFAULT '0' COMMENT 'ǩ',
  `signatureAuditResult` tinyint DEFAULT NULL COMMENT '1:ͨ',
  `signatureAuditOpinion` varchar(100) DEFAULT NULL,
  `signatureAuditUserAccount` varchar(100) DEFAULT NULL COMMENT 'ǩ',
  `signatureAuditUserName` varchar(100) DEFAULT NULL COMMENT 'ǩ',
  `signatureAuditUserDay` date DEFAULT NULL COMMENT 'ǩ',
  `createUserAccount` varchar(100) DEFAULT NULL,
  `createUserName` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifierUserAccount` varchar(100) DEFAULT NULL,
  `modifierUserName` varchar(32) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `version` int DEFAULT NULL,
  `isDel` tinyint DEFAULT NULL COMMENT 'ɾ',
  `sendBillDate` date DEFAULT NULL,
  `costId` int DEFAULT NULL,
  `billLogoName` varchar(255) DEFAULT NULL,
  `billImageName` varchar(255) DEFAULT NULL,
  `orgFile` char(100) DEFAULT NULL,
  `copyFile` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_10` (`portOfLoading`) USING BTREE,
  KEY `FK_Reference_14` (`signatureCompanyId`) USING BTREE,
  KEY `FK_Reference_15` (`receiveCompanyId`) USING BTREE,
  KEY `FK_Reference_16` (`sendCompanyId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of billoflading
-- ----------------------------
INSERT INTO `billoflading` VALUES ('1', null, '1', '2', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `billoflading` VALUES ('2', null, '4', '2', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `billoflading` VALUES ('3', null, '2', '1', '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `billoflading` VALUES ('4', null, '5', '2', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `billoflading` VALUES ('5', null, '2', '3', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `billoflading` VALUES ('6', null, '1', '6', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `billoflading` VALUES ('7', null, '2', '4', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `billoflading` VALUES ('8', null, '2', '4', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `billoflading` VALUES ('9', null, '2', '5', '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `billoflading` VALUES ('10', null, '1', '5', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for billofladingfile
-- ----------------------------
DROP TABLE IF EXISTS `billofladingfile`;
CREATE TABLE `billofladingfile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blId` int DEFAULT NULL,
  `type` tinyint DEFAULT NULL COMMENT '1',
  `isEffective` tinyint DEFAULT NULL COMMENT '1',
  `abandonmentTime` datetime DEFAULT NULL,
  `fileNo` int DEFAULT NULL,
  `printStatus` tinyint DEFAULT NULL,
  `expressId` int DEFAULT NULL,
  `printApplyId` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_12` (`blId`) USING BTREE,
  KEY `FK_Reference_18` (`expressId`) USING BTREE,
  KEY `FK_Reference_19` (`printApplyId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of billofladingfile
-- ----------------------------

-- ----------------------------
-- Table structure for blinvalidapply
-- ----------------------------
DROP TABLE IF EXISTS `blinvalidapply`;
CREATE TABLE `blinvalidapply` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blId` int DEFAULT NULL,
  `type` tinyint DEFAULT NULL COMMENT '0',
  `cause` varchar(200) DEFAULT NULL,
  `destruction` varchar(200) DEFAULT NULL,
  `guarantee` varchar(200) DEFAULT NULL,
  `applyCompanyName` varchar(100) DEFAULT NULL,
  `applyUserAccount` varchar(100) DEFAULT NULL,
  `applyUserName` varchar(100) DEFAULT NULL,
  `applyDay` date DEFAULT NULL,
  `platformAuditResult` tinyint DEFAULT NULL COMMENT '1:ͨ',
  `platformAuditOpinion` varchar(100) DEFAULT NULL COMMENT 'ƽ̨',
  `platformAuditUserAccount` varchar(100) DEFAULT NULL COMMENT 'ƽ̨',
  `platformAuditUserName` varchar(100) DEFAULT NULL COMMENT 'ƽ̨',
  `platformAuditDay` date DEFAULT NULL COMMENT 'ƽ̨',
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_11` (`blId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of blinvalidapply
-- ----------------------------

-- ----------------------------
-- Table structure for bllogo
-- ----------------------------
DROP TABLE IF EXISTS `bllogo`;
CREATE TABLE `bllogo` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `companyId` int DEFAULT NULL COMMENT '公司ID',
  `name` varchar(100) DEFAULT NULL COMMENT 'Logo名称',
  `file` varchar(100) DEFAULT NULL COMMENT 'Logo源文件',
  `uploadDay` date DEFAULT NULL COMMENT '上传时间',
  `isEnable` tinyint DEFAULT NULL COMMENT '启用状态',
  `confirmStatus` tinyint DEFAULT NULL COMMENT '审批状态',
  `comments` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审批意见',
  `createUserAccount` varchar(100) DEFAULT NULL COMMENT '创建人账户',
  `createUserName` varchar(32) DEFAULT NULL COMMENT '创建姓名',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `modifierUserAccount` varchar(100) DEFAULT NULL COMMENT '更新人账户',
  `modifierUserName` varchar(32) DEFAULT NULL COMMENT '更新人姓名',
  `modifyTime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_9` (`companyId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='提单logo表';

-- ----------------------------
-- Records of bllogo
-- ----------------------------
INSERT INTO `bllogo` VALUES ('1', '1', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-28', '1', '2', '优秀', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-28 15:29:46', '4');
INSERT INTO `bllogo` VALUES ('2', '2', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-31', '1', '2', '优秀阿斯顿撒d', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-31 09:56:46', '15');
INSERT INTO `bllogo` VALUES ('3', '3', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-26', '1', '1', '优秀', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-26 18:50:17', '0');
INSERT INTO `bllogo` VALUES ('4', '4', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-26', '1', '1', '数据缺失', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-26 18:50:17', '0');
INSERT INTO `bllogo` VALUES ('5', '5', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-28', '1', '1', 'fdssdv', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-28 15:04:16', '2');
INSERT INTO `bllogo` VALUES ('6', '6', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-26', '1', '1', '优秀', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-26 18:50:17', '0');
INSERT INTO `bllogo` VALUES ('7', '7', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-26', '1', '1', '优秀', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-26 18:50:17', '0');
INSERT INTO `bllogo` VALUES ('8', '8', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-26', '1', '1', '优秀', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-26 18:50:17', '0');
INSERT INTO `bllogo` VALUES ('9', '9', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-26', '1', '1', '优秀', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-26 18:50:17', '0');
INSERT INTO `bllogo` VALUES ('10', '10', '鵅', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-26', '1', '1', '优秀', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-26 18:50:17', '0');
INSERT INTO `bllogo` VALUES ('11', '2', '鵅3', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-31', '1', '1', '优秀阿斯顿撒d', '1', '鵅', '2021-05-27', '1', '鵅', '2021-05-31 10:38:32', '22');
INSERT INTO `bllogo` VALUES ('12', '2', '阿斯顿发', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-31', '2', '1', null, null, null, '2021-05-31', null, null, '2021-05-31 10:46:14', '0');
INSERT INTO `bllogo` VALUES ('13', '2', '发电房', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-31', '2', '1', null, null, null, '2021-05-31', null, null, '2021-05-31 10:47:24', '0');
INSERT INTO `bllogo` VALUES ('14', '2', '发', '/2021/05/31/54010ae8-91cb-48dd-80d0-ca99ed873d13.jpg', '2021-05-31', '2', '1', null, null, null, '2021-05-31', null, null, '2021-05-31 10:48:28', '0');

-- ----------------------------
-- Table structure for busapply
-- ----------------------------
DROP TABLE IF EXISTS `busapply`;
CREATE TABLE `busapply` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `type` int DEFAULT NULL COMMENT '开通服务类型',
  `files` varchar(600) DEFAULT NULL COMMENT '资质证明文件集',
  `companyId` int DEFAULT NULL COMMENT '申请公司',
  `applyAccount` char(100) DEFAULT NULL COMMENT '申请人账号',
  `applyUserName` char(100) DEFAULT NULL COMMENT '申请人姓名',
  `applyDate` date DEFAULT NULL COMMENT '申请时间',
  `auditAccount` char(100) DEFAULT NULL COMMENT '审核人账号',
  `auditUserName` char(100) DEFAULT NULL COMMENT '审核人姓名',
  `auditDate` date DEFAULT NULL COMMENT '审核时间',
  `auditResult` int DEFAULT NULL COMMENT '审核结果',
  `auditStatus` int DEFAULT NULL COMMENT '审核状态',
  `auditOpinion` varchar(500) DEFAULT NULL COMMENT '审核意见',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='服务开通申请表';

-- ----------------------------
-- Records of busapply
-- ----------------------------
INSERT INTO `busapply` VALUES ('1', '1', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '1', '111', '222', '2021-05-29', '222', '222', '2021-05-29', '2', '1', '1555555', '2021-05-30 17:19:59', '0');
INSERT INTO `busapply` VALUES ('3', '1', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '3', '111', '222', '2021-05-29', 'staff', 'staff', '2021-05-30', '2', '1', '111111我是你爹', '2021-04-28 17:20:06', '0');
INSERT INTO `busapply` VALUES ('4', '1', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '4', '111', '222', '2021-05-29', '222', '222', '2021-05-29', '2', '1', '1555555555557897uhfghfgh', '2021-05-26 17:20:14', '0');
INSERT INTO `busapply` VALUES ('5', '2', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '2', '111', '222', '2021-05-29', 'staff', 'staff', '2021-05-30', '1', '1', '15555555555555555555我是你爹', '2021-05-12 17:20:09', '0');
INSERT INTO `busapply` VALUES ('6', '3', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '3', '111', '222', '2021-05-29', 'staff', 'staff', '2021-06-01', '1', '1', '18888888888888', '2021-06-01 13:44:29', '1');
INSERT INTO `busapply` VALUES ('7', '2', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '3', '111', '222', '2021-05-29', '222', '222', '2021-05-29', '2', '1', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', '2021-05-28 17:20:22', '0');
INSERT INTO `busapply` VALUES ('8', '2', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '2', '111', '222', '2021-05-29', '222', '222', '2021-05-29', '1', '1', '2', '2021-05-21 17:20:26', '0');
INSERT INTO `busapply` VALUES ('11', '3', '/2021/05/30/dc5cdba8-0c54-4529-96a5-08e75f2f3d3d.png,', '2', 'user', 'user', '2021-05-30', 'staff', 'staff', '2021-05-31', '1', '1', '777777777', '2021-05-31 15:07:24', '1');
INSERT INTO `busapply` VALUES ('12', '2', '/2021/05/31/8ae99866-8952-465b-b04b-fb1e2fde069d.png,/2021/05/31/4da588bb-4028-4045-9b0d-fd514a15ca9d.png,', '2', 'user', 'user', '2021-05-31', 'staff', 'staff', '2021-05-31', '1', '1', null, '2021-05-31 15:06:09', '6');
INSERT INTO `busapply` VALUES ('18', '1', '/2021/05/31/efb32033-8148-4df2-93e6-245d1a43195d.JPG,/2021/05/31/9a188fb7-dfe8-444a-9f39-3eb9449f9716.png,', '2', 'user', 'user', '2021-05-31', 'staff', 'staff', '2021-05-31', '1', '1', '5674684769', '2021-05-31 15:05:31', '5');
INSERT INTO `busapply` VALUES ('19', '1', '/2021/05/31/9a7d2f57-6b6c-4dd8-a10f-1ac45426165e.png,', '2', 'user', 'user', '2021-05-31', 'staff', 'staff', '2021-06-01', '2', '2', '就是不通过，怎么滴', '2021-06-01 13:51:08', '2');
INSERT INTO `busapply` VALUES ('24', '1', '/2021/05/31/f0d3a939-69f3-4e23-9ca7-a00d4bd65f21.png,', '2', 'user', 'user', '2021-05-31', 'staff', 'staff', '2021-05-31', '1', '1', null, '2021-05-31 15:49:43', '1');
INSERT INTO `busapply` VALUES ('31', '1', '/2021/06/01/1942c2ac-062f-4550-bc6c-a0e0dc747640.jpg,', '2', 'user', 'user', '2021-06-01', 'staff', 'staff', '2021-06-01', '2', '1', null, '2021-06-01 08:35:43', '1');
INSERT INTO `busapply` VALUES ('32', '3', '/2021/06/01/122d06e9-55ff-4e79-b4a8-2d18b1531504.jpg,', '2', 'user', 'user', '2021-06-01', 'staff', 'staff', '2021-06-01', '1', '1', null, '2021-06-01 10:48:02', '1');
INSERT INTO `busapply` VALUES ('33', '2', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '5', '111', '222', '2021-05-29', 'staff', 'staff', '2021-05-30', '2', '1', '15555555555555555555我是你爹', '2021-05-12 17:20:09', '0');
INSERT INTO `busapply` VALUES ('34', '1', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '5', '111', '222', '2021-05-29', 'staff', 'staff', '2021-05-30', '2', '1', '15555555555555555555我是你爹', '2021-05-12 17:20:09', '0');
INSERT INTO `busapply` VALUES ('35', '3', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '5', '111', '222', '2021-05-29', 'staff', 'staff', '2021-05-30', '2', '1', '15555555555555555555我是你爹', '2021-05-12 17:20:09', '0');
INSERT INTO `busapply` VALUES ('36', '3', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '4', '111', '222', '2021-05-29', 'staff', 'staff', '2021-05-30', '2', '1', '15555555555555555555我是你爹', '2021-05-12 17:20:09', '0');
INSERT INTO `busapply` VALUES ('37', '2', '/2021/05/26/a.jpg,/2021/05/26/b.jpg,/2021/05/26/c.png', '4', '111', '222', '2021-05-29', 'staff', 'staff', '2021-05-30', '2', '1', '15555555555555555555我是你爹', '2021-05-12 17:20:09', '0');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称',
  `companyTypeId` int NOT NULL COMMENT '公司类型ID',
  `memberType` int DEFAULT NULL COMMENT '会员类型',
  `level` char(100) DEFAULT NULL COMMENT '客户等级',
  `countryId` int DEFAULT NULL COMMENT '所在国家Id',
  `provinceId` int DEFAULT NULL COMMENT '所在省份',
  `cityId` int DEFAULT NULL COMMENT '所在城市',
  `address` char(200) DEFAULT NULL COMMENT '详细地址',
  `creditCode` char(100) DEFAULT NULL COMMENT '企业信用代码',
  `cerificateAttachment1` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组织机构代码证附件1AAAA',
  `cerificateAttachment2` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组织结构代码证附件2AAAA',
  `email` char(100) DEFAULT NULL COMMENT '企业联系邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业管理员手机号',
  `adminAccountId` int DEFAULT NULL COMMENT '企业管理员账号IDAAAA',
  `status` int DEFAULT NULL COMMENT '企业状态',
  `isSendBl` tinyint DEFAULT NULL COMMENT '是否开通出单服务',
  `isReceive` tinyint DEFAULT NULL COMMENT '是否开通发单服务',
  `isEnableSeal` tinyint DEFAULT NULL COMMENT '是否开通签章服务',
  `createDate` date DEFAULT NULL COMMENT '创建时间',
  `createUserId` int DEFAULT NULL COMMENT '创建人账号ID',
  `createUserName` char(50) DEFAULT NULL COMMENT '创建人姓名',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  `modifyUserId` int DEFAULT NULL COMMENT '修改人账号ID',
  `modifyUserName` char(50) DEFAULT NULL COMMENT '修改人姓名',
  `isDel` tinyint DEFAULT NULL COMMENT '删除标记',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_1` (`provinceId`) USING BTREE,
  KEY `FK_Reference_2` (`cityId`) USING BTREE,
  KEY `FK_Reference_22` (`companyTypeId`) USING BTREE,
  KEY `FK_Reference_23` (`memberType`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='企业信息表';

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '中远海运', '1', '1', '3376', '1', '4', '5', '34', '1', '/2021/05/26/b.jpg', '/2021/05/26/b.jpg', '1', '232143', '1', '1', '1', '1', '1', '2021-05-31', '1', '1', '2021-05-25 00:00:00', '1', '1', '0', '0');
INSERT INTO `company` VALUES ('2', '大连智森', '1', '2', '3377', '1', '4', '5', '34', '2', '/2021/05/26/b.jpg', '/2021/05/26/b.jpg', '2', '465465', '2', '2', '2', '2', '2', '2021-05-31', null, null, null, null, null, '0', '0');
INSERT INTO `company` VALUES ('3', '大连校联', '1', '2', '3376', '1', '4', '5', '34', '1', '/2021/05/26/b.jpg', '/2021/05/26/b.jpg', '1', '78657567', '1', '1', '1', '1', '1', '2021-05-31', '1', '1', '2021-05-25 00:00:00', '1', '1', '0', '0');
INSERT INTO `company` VALUES ('4', '大连永安', '1', '3', '3376', '1', '4', '5', '34', '2', '/2021/05/26/b.jpg', '/2021/05/26/b.jpg', '2', '67856765', '2', '2', '2', '2', '2', '2021-05-31', null, null, null, null, null, '0', '0');
INSERT INTO `company` VALUES ('5', '大连圣亿', '1', '3', '3377', '1', '4', '5', '34', '2', '/2021/05/26/b.jpg', '/2021/05/26/b.jpg', '2', '657567', '2', '2', '2', '2', '2', '2021-05-31', null, null, null, null, null, '0', '0');
INSERT INTO `company` VALUES ('7', '大连智森6', '4', '3', '3377', '1', '4', '5', '34', '5766', '/2021/05/26/b.jpg', '/2021/05/26/b.jpg', '980', '897879', '89', '2', '1', '1', '3', '2021-05-31', '3', '3', '2021-05-26 14:40:07', '3', '3', '0', '0');
INSERT INTO `company` VALUES ('8', '大连智森73', '4', '3', '3378', '1', '4', '5', '34', '5766', '/2021/05/26/b.jpg', '/2021/05/26/b.jpg', '980', '897879', '89', '9', '1', '1', '3', '2021-05-31', '3', '3', '2021-05-27 13:26:17', '3', '3', '0', '2');
INSERT INTO `company` VALUES ('9', '大连智森8', '4', '4', '3377', '1', '4', '5', '34', '5766', '/2021/05/26/b.jpg', '/2021/05/26/b.jpg', '980', '897879', '89', '2', '1', '1', '3', '2021-05-31', '3', '3', '2021-05-26 14:40:07', '3', '3', '0', '0');
INSERT INTO `company` VALUES ('10', '大连智森9', '4', '5', '3377', '1', '4', '5', '34', '5766', '/2021/05/26/b.jpg', '/2021/05/26/b.jpg', '980', '897879', '89', '2', '1', '1', '3', '2021-05-31', '3', '3', '2021-05-26 14:40:07', '3', '3', '0', '0');

-- ----------------------------
-- Table structure for companytype
-- ----------------------------
DROP TABLE IF EXISTS `companytype`;
CREATE TABLE `companytype` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `name` char(100) DEFAULT NULL COMMENT '类型名称',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `createUserId` int DEFAULT NULL COMMENT '创建人账号ID',
  `createUserName` char(50) DEFAULT NULL COMMENT '创建人姓名',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  `modifyUserId` int DEFAULT NULL COMMENT '修改人账号ID',
  `modifyUserName` char(50) DEFAULT NULL COMMENT '修改人姓名',
  `isDel` tinyint DEFAULT NULL COMMENT '删除标记',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公司类型';

-- ----------------------------
-- Records of companytype
-- ----------------------------
INSERT INTO `companytype` VALUES ('1', 'dfsadf', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '1', '1');
INSERT INTO `companytype` VALUES ('2', 'ty', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('3', 'jgybuin', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('4', '奇拿', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('5', '骄傲', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('6', '讲', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '1', '1');
INSERT INTO `companytype` VALUES ('7', '集', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('8', '就挨个你', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('9', 'egfd', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('10', 'aaa', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('11', 'qweqe', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('12', '啊啊', '2021-05-25 00:00:00', null, null, '2021-05-25 00:00:00', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('13', 'jaidbnAB', '2021-05-25 11:21:24', null, null, '2021-05-25 11:21:24', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('14', 'sdfasd', '2021-05-25 13:10:57', null, null, '2021-05-25 13:10:57', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('15', '阿斯蒂芬', '2021-05-28 20:52:54', null, null, '2021-05-28 20:52:54', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('16', '撒地方', '2021-05-28 20:53:19', null, null, '2021-05-28 20:53:19', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('17', '是否', '2021-05-28 20:54:51', null, null, '2021-05-28 20:54:51', null, null, '0', '0');
INSERT INTO `companytype` VALUES ('18', '士大夫', '2021-05-28 20:58:31', null, null, '2021-05-28 20:58:31', null, null, '0', '0');

-- ----------------------------
-- Table structure for cuslevel
-- ----------------------------
DROP TABLE IF EXISTS `cuslevel`;
CREATE TABLE `cuslevel` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `name` char(100) DEFAULT NULL COMMENT '级别名称',
  `low` int DEFAULT NULL COMMENT '消费下限',
  `hi` int DEFAULT NULL COMMENT '消费上限',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `createUserId` int DEFAULT NULL COMMENT '创建人账号ID',
  `createUserName` char(50) DEFAULT NULL COMMENT '创建人姓名',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  `modifyUserId` int DEFAULT NULL COMMENT '修改人账号ID',
  `modifyUserName` char(50) DEFAULT NULL COMMENT '修改人姓名',
  `isDel` tinyint DEFAULT NULL COMMENT '删除标记',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3379 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='客户级别';

-- ----------------------------
-- Records of cuslevel
-- ----------------------------
INSERT INTO `cuslevel` VALUES ('3376', 'VIP1', '10', '25', '2021-05-25 09:55:43', null, null, '2021-05-25 09:55:43', null, null, '0', '0');
INSERT INTO `cuslevel` VALUES ('3377', 'VIP2', '123', '1234', '2021-05-31 08:20:07', null, null, '2021-05-31 08:21:48', null, null, '1', '1');
INSERT INTO `cuslevel` VALUES ('3378', 'VIP3', '1500', '2000', '2021-05-31 08:30:26', null, null, '2021-05-31 08:33:16', null, null, '0', '4');

-- ----------------------------
-- Table structure for express
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(100) DEFAULT NULL,
  `expressCompanyId` int DEFAULT NULL,
  `sendDay` date DEFAULT NULL,
  `sendAddress` varchar(100) DEFAULT NULL,
  `sendPerson` varchar(100) DEFAULT NULL,
  `sendPhone` varchar(100) DEFAULT NULL,
  `collectAddress` varchar(100) DEFAULT NULL,
  `collectPerson` varchar(100) DEFAULT NULL,
  `collectPhone` varchar(100) DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '0',
  `recordTime` datetime DEFAULT NULL,
  `recordUserAccount` varchar(100) DEFAULT NULL,
  `recordUserName` varchar(100) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_20` (`expressCompanyId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of express
-- ----------------------------

-- ----------------------------
-- Table structure for expresscompany
-- ----------------------------
DROP TABLE IF EXISTS `expresscompany`;
CREATE TABLE `expresscompany` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `name` char(100) DEFAULT NULL COMMENT '快递公司名称',
  `quantity` int DEFAULT NULL COMMENT '累计发单量',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `createUserId` int DEFAULT NULL COMMENT '创建人账号ID',
  `createUserName` char(50) DEFAULT NULL COMMENT '创建人姓名',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  `modifyUserId` int DEFAULT NULL COMMENT '修改人账号ID',
  `modifyUserName` char(50) DEFAULT NULL COMMENT '修改人姓名',
  `isDel` tinyint DEFAULT NULL COMMENT '删除标记',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='快递公司表';

-- ----------------------------
-- Records of expresscompany
-- ----------------------------
INSERT INTO `expresscompany` VALUES ('1', '中通快递公司', '100', '2021-05-24 09:13:39', null, null, '2021-05-24 09:13:39', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('2', '申通快递公司', '50', '2021-05-24 09:13:50', null, null, '2021-05-24 09:13:50', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('3', '公司', '10', '2021-05-24 09:18:02', null, null, '2021-05-24 09:18:02', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('4', '公司', '1', '2021-05-24 09:18:13', null, null, '2021-05-24 09:18:13', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('5', '公司', '0', '2021-05-24 09:18:16', null, null, '2021-05-24 09:18:16', null, '0', '0', '0');
INSERT INTO `expresscompany` VALUES ('6', '邮政快递公司', '485', '2021-05-24 09:18:19', null, null, '2021-05-24 09:18:19', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('7', '公司', '13', '2021-05-24 09:18:23', null, null, '2021-05-24 09:18:23', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('8', '公司', '56', '2021-05-24 09:18:28', null, null, '2021-05-24 09:18:28', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('9', '韵达快递公司', '757', '2021-05-24 09:18:35', null, null, '2021-05-24 09:18:35', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('10', '顺风快递公司', '456', '2021-05-24 09:18:41', null, null, '2021-05-24 09:18:41', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('11', '公司', '41', '2021-05-24 09:18:46', null, null, '2021-05-24 09:18:46', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('12', '公司', null, '2021-05-24 11:44:23', null, null, '2021-05-24 11:44:23', null, null, '1', '1');
INSERT INTO `expresscompany` VALUES ('13', '公司', '0', '2021-05-24 11:45:11', null, null, '2021-05-24 11:45:11', null, null, '1', '1');
INSERT INTO `expresscompany` VALUES ('14', '公司', '0', '2021-05-24 13:00:38', null, null, '2021-05-24 13:00:38', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('15', '公司', '0', '2021-05-24 13:00:41', null, null, '2021-05-24 13:00:41', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('16', '公司', '0', '2021-05-24 13:24:44', null, null, '2021-05-24 13:24:44', null, null, '1', '4');
INSERT INTO `expresscompany` VALUES ('17', '公司', '0', '2021-05-25 10:35:52', null, null, '2021-05-25 10:35:52', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('18', '公司', '0', '2021-05-25 10:35:58', null, null, '2021-05-25 10:35:58', null, null, '1', '1');
INSERT INTO `expresscompany` VALUES ('19', '公司', '0', '2021-05-25 11:12:20', null, null, '2021-05-25 11:12:20', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('20', '公司', '0', '2021-05-25 11:16:11', null, null, '2021-05-25 11:16:11', null, null, '1', '4');
INSERT INTO `expresscompany` VALUES ('21', '公司', '0', '2021-05-25 11:18:32', null, null, '2021-05-25 11:18:32', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('22', '东北公司', '0', '2021-05-25 11:18:43', null, null, '2021-05-25 11:18:43', null, null, '1', '1');
INSERT INTO `expresscompany` VALUES ('23', '西北公司', '0', '2021-05-25 11:18:48', null, null, '2021-05-25 11:18:48', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('24', '南公司', '0', '2021-05-25 11:19:14', null, null, '2021-05-25 11:19:14', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('25', '东南公司', '0', '2021-05-25 11:21:29', null, null, '2021-05-25 11:21:29', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('26', '西南公司', '0', '2021-05-25 11:22:28', null, null, '2021-05-25 11:22:28', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('27', '北公司', '0', '2021-05-25 11:22:35', null, null, '2021-05-25 11:22:35', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('28', '东公司', '0', '2021-05-25 11:22:40', null, null, '2021-05-25 11:22:40', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('29', '西公司', '0', '2021-05-25 11:22:52', null, null, '2021-05-25 11:22:52', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('30', '小城市公司', '1', '2021-05-26 14:11:20', null, null, '2021-05-31 12:55:43', null, null, '0', '5');
INSERT INTO `expresscompany` VALUES ('31', '科技公益公司', '0', '2021-05-26 14:48:19', null, null, '2021-05-26 14:48:19', null, null, '1', '1');
INSERT INTO `expresscompany` VALUES ('32', '投入公司', '0', '2021-05-26 14:48:26', null, null, '2021-05-26 14:48:26', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('33', '东方公司', '0', '2021-05-26 14:53:59', null, null, '2021-05-26 14:53:59', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('34', '公司', '0', '2021-05-26 14:55:15', null, null, '2021-05-26 14:55:15', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('35', '公司', '0', '2021-05-26 14:55:41', null, null, '2021-05-26 14:55:41', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('36', '公司', '0', '2021-05-26 15:13:53', null, null, '2021-05-26 15:13:53', null, null, '1', '2');
INSERT INTO `expresscompany` VALUES ('37', '公司', '0', '2021-05-26 15:20:55', null, null, '2021-05-26 15:20:55', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('38', '公司', '0', '2021-05-26 15:42:35', null, null, '2021-05-26 15:42:35', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('39', '公司', '0', '2021-05-26 15:46:00', null, null, '2021-05-26 15:46:00', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('40', '公司', '0', '2021-05-26 15:46:07', null, null, '2021-05-26 15:46:07', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('41', '公司', '0', '2021-05-26 15:46:24', null, null, '2021-05-26 15:46:24', null, null, '0', '1');
INSERT INTO `expresscompany` VALUES ('42', '公司', '0', '2021-05-26 15:46:27', null, null, '2021-05-26 15:46:27', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('43', '公司', '0', '2021-05-26 15:46:31', null, null, '2021-05-26 15:46:31', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('44', '公司', '0', '2021-05-26 15:46:34', null, null, '2021-05-26 15:46:34', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('45', '公司', '0', '2021-05-26 15:46:52', null, null, '2021-05-26 15:46:52', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('46', '公司', '0', '2021-05-26 15:47:02', null, null, '2021-05-26 15:47:02', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('47', '公司', '0', '2021-05-26 15:47:07', null, null, '2021-05-26 15:47:07', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('48', '公司', '0', '2021-05-26 15:47:12', null, null, '2021-05-26 15:47:12', null, null, '1', '0');
INSERT INTO `expresscompany` VALUES ('49', '公司', '0', '2021-05-26 15:47:16', null, null, '2021-05-26 15:47:16', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('50', '公司', '0', '2021-05-26 15:47:19', null, null, '2021-05-26 15:47:19', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('51', '公司', '0', '2021-05-26 15:53:10', null, null, '2021-05-26 15:53:10', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('52', '公司', '0', '2021-05-26 15:53:26', null, null, '2021-05-26 15:53:26', null, null, '0', '0');
INSERT INTO `expresscompany` VALUES ('53', '公司', '0', '2021-05-31 08:23:31', null, null, '2021-05-31 08:23:31', null, null, '1', '0');

-- ----------------------------
-- Table structure for membertype
-- ----------------------------
DROP TABLE IF EXISTS `membertype`;
CREATE TABLE `membertype` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `name` char(100) DEFAULT NULL COMMENT '类型名称',
  `discount` int DEFAULT NULL COMMENT '折扣比例',
  `amount` int DEFAULT NULL COMMENT '充值金额',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='会员类型';

-- ----------------------------
-- Records of membertype
-- ----------------------------
INSERT INTO `membertype` VALUES ('1', '青铜', '12', '132', '2021-05-31 08:34:57', '3');
INSERT INTO `membertype` VALUES ('2', '白银', '3', '2', '2021-05-24 09:10:33', '0');
INSERT INTO `membertype` VALUES ('3', '黄金', '33', '2222', '2021-05-24 09:56:49', '0');
INSERT INTO `membertype` VALUES ('4', '铂金', '4', '2', '2021-05-24 09:59:28', '0');
INSERT INTO `membertype` VALUES ('5', '钻石', '5', '2', '2021-05-12 09:59:33', '0');

-- ----------------------------
-- Table structure for port
-- ----------------------------
DROP TABLE IF EXISTS `port`;
CREATE TABLE `port` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `name` char(100) DEFAULT NULL COMMENT '港口名称',
  `eName` char(100) DEFAULT NULL COMMENT '港口英文名',
  `countryId` int DEFAULT NULL COMMENT '所在国家ID',
  `provinceId` int DEFAULT NULL COMMENT '所在省份',
  `cityId` int DEFAULT NULL COMMENT '所在城市',
  `outlay` int DEFAULT NULL COMMENT '出单费用',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `createUserId` int DEFAULT NULL COMMENT '创建人账号ID',
  `createUserName` char(50) DEFAULT NULL COMMENT '创建人姓名',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  `modifyUserId` int DEFAULT NULL COMMENT '修改人账号ID',
  `modifyUserName` char(50) DEFAULT NULL COMMENT '修改人姓名',
  `isDel` tinyint DEFAULT NULL COMMENT '删除标记',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_24` (`countryId`) USING BTREE,
  KEY `FK_Reference_25` (`provinceId`) USING BTREE,
  KEY `FK_Reference_26` (`cityId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='港口信息表';

-- ----------------------------
-- Records of port
-- ----------------------------
INSERT INTO `port` VALUES ('1', 'qqq', 'qqq', '2', '32', '33', '25252', '2021-05-24 19:36:46', null, null, '2021-05-24 21:36:40', null, null, '0', '4');
INSERT INTO `port` VALUES ('2', 'aaa', 'aa', '2', '40', '41', '1000', '2021-05-24 19:37:36', null, null, '2021-05-24 19:37:36', null, null, '1', '1');
INSERT INTO `port` VALUES ('3', 'bb', 'bb', '1', '18', '31', '22', '2021-05-24 19:39:11', null, null, '2021-05-24 19:39:11', null, null, '1', '1');
INSERT INTO `port` VALUES ('4', 'sad', 'sad', '1', '18', '19', '123', '2021-05-24 19:39:45', null, null, '2021-05-24 19:39:45', null, null, '1', '1');
INSERT INTO `port` VALUES ('5', 'sss', 'sss', '2', '38', '39', '123', '2021-05-24 20:40:45', null, null, '2021-05-24 20:40:45', null, null, '1', '4');
INSERT INTO `port` VALUES ('6', '阿斯顿', '萨达', '1', '18', '19', '1212', '2021-05-24 21:16:30', null, null, '2021-05-24 21:16:30', null, null, '1', '1');
INSERT INTO `port` VALUES ('7', '查出', 'c', '2', '32', '33', '2000000', '2021-05-25 08:12:23', null, null, '2021-05-25 08:12:23', null, null, '0', '0');
INSERT INTO `port` VALUES ('8', 'qqqa', 'qqqa', '1', '4', '5', '200', '2021-05-25 08:13:56', null, null, '2021-05-25 08:13:56', null, null, '0', '0');
INSERT INTO `port` VALUES ('9', 'qqqb', 'qqqb', '2', '34', '35', '10000', '2021-05-25 08:14:32', null, null, '2021-05-25 08:14:32', null, null, '0', '0');
INSERT INTO `port` VALUES ('10', 'qq   q', 'q  q', '1', '12', '17', '500000', '2021-05-25 08:15:01', null, null, '2021-05-25 08:15:01', null, null, '0', '0');
INSERT INTO `port` VALUES ('11', 'aaa', 'aaa', '1', '6', '7', '1000', '2021-05-25 10:12:35', null, null, '2021-05-25 10:12:35', null, null, '0', '0');
INSERT INTO `port` VALUES ('12', 'safsgfsrg', 'sdfsd', '1', '18', '19', '10000', '2021-05-26 13:51:08', null, null, '2021-05-26 13:51:08', null, null, '1', '1');
INSERT INTO `port` VALUES ('13', 'vbn', 'sdafd', '1', '4', '5', '2210', '2021-05-26 13:52:28', null, null, '2021-05-31 12:45:40', null, null, '0', '1');
INSERT INTO `port` VALUES ('14', 'gfdd', 'rhgdfghj', '1', '4', '5', '4010210', '2021-05-26 13:52:42', null, null, '2021-05-26 13:52:42', null, null, '1', '1');
INSERT INTO `port` VALUES ('15', 'fghj', 'wasrtyuio', '1', '4', '5', '520014520', '2021-05-26 13:52:49', null, null, '2021-05-26 14:36:49', null, null, '1', '2');
INSERT INTO `port` VALUES ('16', 'Ffff', 'dfghjkhgfdssdf', '1', '4', '5', '6564656', '2021-05-26 14:32:14', null, null, '2021-05-31 12:44:24', null, null, '1', '4');
INSERT INTO `port` VALUES ('17', '汉克', 'hanke', '2', '32', '33', '123', '2021-05-27 08:39:00', null, null, '2021-05-27 08:39:00', null, null, '1', '1');
INSERT INTO `port` VALUES ('18', 'A   DS', 'hh', '1', '4', '5', '111', '2021-05-27 08:59:49', null, null, '2021-05-27 09:04:02', null, null, '1', '2');
INSERT INTO `port` VALUES ('19', 'WAS', 'WAS', '1', '4', '5', '11', '2021-05-27 09:04:49', null, null, '2021-05-27 09:04:49', null, null, '1', '1');
INSERT INTO `port` VALUES ('20', 'FEA', 'ADF', '1', '4', '5', '123', '2021-05-27 09:06:13', null, null, '2021-05-27 09:06:13', null, null, '1', '1');
INSERT INTO `port` VALUES ('21', 'AS', 'SA', '1', '4', '5', '123123', '2021-05-27 09:06:43', null, null, '2021-05-27 09:06:43', null, null, '1', '1');
INSERT INTO `port` VALUES ('22', 'Fff', 'hahaah', '1', '4', '5', '123', '2021-05-31 12:43:16', null, null, '2021-05-31 12:43:16', null, null, '1', '1');
INSERT INTO `port` VALUES ('23', '对 的', 'HAH', '1', '4', '5', '321', '2021-05-31 12:47:04', null, null, '2021-05-31 12:49:07', null, null, '0', '1');
INSERT INTO `port` VALUES ('24', 'baba', 'dsd', '1', '4', '5', '3333', '2021-05-31 12:47:23', null, null, '2021-05-31 13:10:32', null, null, '0', '1');
INSERT INTO `port` VALUES ('25', '大大', 'bbbbbb', '1', '4', '5', '43343443', '2021-05-31 12:47:38', null, null, '2021-05-31 12:47:38', null, null, '0', '0');
INSERT INTO `port` VALUES ('26', 'gfdhf', 'hfh', '1', '4', '5', '435463', '2021-05-31 12:47:47', null, null, '2021-05-31 12:47:47', null, null, '0', '0');

-- ----------------------------
-- Table structure for printapply
-- ----------------------------
DROP TABLE IF EXISTS `printapply`;
CREATE TABLE `printapply` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blId` int DEFAULT NULL,
  `type` tinyint DEFAULT NULL COMMENT '1',
  `applyUserAccount` varchar(100) DEFAULT NULL,
  `applyUserName` varchar(100) DEFAULT NULL,
  `applyTime` datetime DEFAULT NULL,
  `printStatus` tinyint DEFAULT NULL COMMENT '0:   δ',
  `printTime` datetime DEFAULT NULL,
  `printUserAccount` varchar(100) DEFAULT NULL,
  `printUserName` varchar(100) DEFAULT NULL,
  `firstAuditResult` tinyint DEFAULT NULL COMMENT '1:ͨ',
  `firstAuditOpinion` varchar(100) DEFAULT NULL,
  `firstAuditUserAccount` varchar(100) DEFAULT NULL,
  `firstAuditUserName` varchar(100) DEFAULT NULL,
  `firstAuditUserDay` date DEFAULT NULL,
  `receiverName` varchar(32) DEFAULT NULL,
  `receiverPhone` varchar(20) DEFAULT NULL,
  `receiverAddress` varchar(255) DEFAULT NULL,
  `version` int DEFAULT NULL,
  `payMoney` double(10,2) DEFAULT NULL COMMENT '֧',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_13` (`blId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of printapply
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` char(50) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createUserId` int DEFAULT NULL,
  `createUserName` char(50) DEFAULT NULL,
  `modifyDate` date DEFAULT NULL,
  `modifyUserId` int DEFAULT NULL,
  `modifyUserName` char(50) DEFAULT NULL,
  `isDel` tinyint DEFAULT NULL,
  `version` int DEFAULT NULL,
  `companyID` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_6` (`companyID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for roleaccountref
-- ----------------------------
DROP TABLE IF EXISTS `roleaccountref`;
CREATE TABLE `roleaccountref` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleID` int DEFAULT NULL,
  `accountID` int DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createUserId` int DEFAULT NULL,
  `createUserName` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_4` (`roleID`) USING BTREE,
  KEY `FK_Reference_5` (`accountID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of roleaccountref
-- ----------------------------

-- ----------------------------
-- Table structure for seal
-- ----------------------------
DROP TABLE IF EXISTS `seal`;
CREATE TABLE `seal` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `companyId` int DEFAULT NULL COMMENT '公司ID',
  `name` varchar(100) DEFAULT NULL COMMENT '印章名称',
  `file` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '印章源文件',
  `uploadDay` date DEFAULT NULL COMMENT '上传日期',
  `isEnable` tinyint DEFAULT NULL COMMENT '启用状态',
  `sealType` tinyint DEFAULT NULL COMMENT '印章类型',
  `confirmStatus` tinyint DEFAULT NULL COMMENT '印章确认状态',
  `comments` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审批意见',
  `createUserAccount` varchar(100) DEFAULT NULL COMMENT '创建人账户',
  `createUserName` varchar(32) DEFAULT NULL COMMENT '创建姓名',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifierUserAccount` varchar(100) DEFAULT NULL COMMENT '更新人账户',
  `modifierUserName` varchar(32) DEFAULT NULL COMMENT '更新人姓名',
  `modifyTime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_8` (`companyId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='印章表';

-- ----------------------------
-- Records of seal
-- ----------------------------
INSERT INTO `seal` VALUES ('1', '1', 'A', '/2021/05/27/c215903d-9c74-4732-8350-c03eb0b5ad0a.JPG', '2021-05-26', '2', '2', '1', '1111111www111o', '1', null, '2021-05-26 18:17:02', null, null, '2021-05-27 14:01:19', '25');
INSERT INTO `seal` VALUES ('3', '2', 'aaa', '/2021/05/27/c215903d-9c74-4732-8350-c03eb0b5ad0a.JPG', '2021-05-27', '2', '2', '4', null, null, null, '2021-05-27 13:52:14', null, null, '2021-05-28 14:32:27', '8');
INSERT INTO `seal` VALUES ('4', '2', 'aaa1', '/2021/05/27/c215903d-9c74-4732-8350-c03eb0b5ad0a.JPG', '2021-05-27', '2', '2', '3', null, null, null, '2021-05-27 13:53:12', null, null, '2021-05-27 14:01:36', '1');
INSERT INTO `seal` VALUES ('5', '2', 'yyy', '/2021/05/27/c215903d-9c74-4732-8350-c03eb0b5ad0a.JPG', '2021-05-27', '1', '1', '4', null, null, null, '2021-05-27 14:04:45', null, null, '2021-05-28 14:54:43', '29');
INSERT INTO `seal` VALUES ('6', '3', 'bbb', '/2021/05/27/c215903d-9c74-4732-8350-c03eb0b5ad0a.JPG', '2021-05-27', '2', '2', '3', '112222', null, null, '2021-05-27 14:04:45', null, null, '2021-05-28 08:38:14', '2');
INSERT INTO `seal` VALUES ('7', '2', 'bbbqadwf', '/2021/05/27/c215903d-9c74-4732-8350-c03eb0b5ad0a.JPG', '2021-05-27', '1', '2', '1', '啊啊啊bbba啊啊啊啊', null, null, '2021-05-27 14:21:41', null, null, '2021-05-28 14:54:37', '34');
