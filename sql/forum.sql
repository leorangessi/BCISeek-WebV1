/*
 Navicat Premium Dump SQL

 Source Server         : gen
 Source Server Type    : MySQL
 Source Server Version : 50721 (5.7.21-log)
 Source Host           : localhost:3306
 Source Schema         : forum

 Target Server Type    : MySQL
 Target Server Version : 50721 (5.7.21-log)
 File Encoding         : 65001

 Date: 14/03/2026 17:20:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论唯一标识',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `post_id` int(11) NOT NULL COMMENT '关联文章ID（关联post.id）',
  `author_id` int(11) NOT NULL COMMENT '评论作者ID（关联user.id）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论发布时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_post_id`(`post_id`) USING BTREE,
  INDEX `idx_author_id`(`author_id`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评论表：存储文章评论（依赖文章可见范围）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限规则唯一标识',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '匹配角色：visitor/user/admin',
  `resource_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源类型：post（文章）/comment（评论）',
  `operations` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '允许的操作（逗号分隔，如query,add,delete_self）',
  `visible_roles` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '可访问的可见范围（逗号分隔，如visitor,user）',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限规则描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_resource`(`role`, `resource_type`) USING BTREE,
  INDEX `idx_role`(`role`) USING BTREE,
  INDEX `idx_resource_type`(`resource_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '权限规则表：存储不同角色的操作权限+可见范围规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'visitor', 'post', 'query', 'visitor', '游客仅可查看对游客可见的文章');
INSERT INTO `permission` VALUES (2, 'visitor', 'comment', 'query', 'visitor', '游客仅可查看对游客可见文章的评论');
INSERT INTO `permission` VALUES (3, 'user', 'post', 'query,add,delete_self,update_self', 'visitor,user', '普通用户可查看非仅管理员可见的文章，增删改自己的文章');
INSERT INTO `permission` VALUES (4, 'user', 'comment', 'query,add,delete_self,update_self', 'visitor,user', '普通用户可查看非仅管理员可见文章的评论，增删改自己的评论');
INSERT INTO `permission` VALUES (5, 'admin', 'post', 'query,add,delete,update', 'visitor,user,admin', '管理员可查看所有文章，增删改所有文章，可修改可见范围');
INSERT INTO `permission` VALUES (6, 'admin', 'comment', 'query,add,delete,update', 'visitor,user,admin', '管理员可查看所有评论，增删改所有评论');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章唯一标识',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
  `author_id` int(11) NOT NULL COMMENT '文章作者ID（关联user.id）',
  `visibility` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'visitor' COMMENT '可见范围：admin（仅管理员）/user（用户+管理员）/visitor（全员）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章发布时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '文章最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_author_id`(`author_id`) USING BTREE,
  INDEX `idx_visibility`(`visibility`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章表：存储文章内容+可见范围控制' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (17, '全球首款侵入式脑机接口医疗器械获批上市 脑机接口如何“接”入未来?', '      中国在脑机接口这一前沿医疗科技领域实现“全球首发”。3月13日，博睿康医疗科技（上海）有限公司自主研发的“植入式脑机接口手部运动功能代偿系统”获批上市，成为全球首款正式获批的侵入式脑机接口医疗器械。\n       据悉，该产品适用于颈段脊髓损伤所致四肢瘫患者，通过气动手套设备辅助实现手部的抓握功能代偿。患者需满足以下条件：18岁到60岁，C2～C6颈段脊髓损伤评级A～C级的四肢瘫，疾病确诊超过1年且经规范治疗后病情稳定至少6个月，手部无法完成抓握，上臂尚存部分功能。\n      近年来，随着神经信号处理、生物兼容材料、人工智能等技术接连取得突破，脑机接口开始从科幻场景走向临床应用，给许多患者的治疗与康复带来新希望。\n\n       “十五五”规划建议明确提出，“推动量子科技、生物制造、氢能和核聚变能、脑机接口、具身智能、第六代移动通信等成为新的经济增长点”。\n\n       科技部部长阴和俊在十四届全国人大四次会议首场“部长通道”表示，“十五五”时期，要抓紧部署实施一批国家重大科技项目，特别是要加强集成电路、人工智能、脑机接口等领域科技攻关，为产业发展提供更强有力的科技支撑。\n\n       全国政协委员、天津大学副校长明东此前接受采访时表示，2025年3月国家医疗保障局为脑机接口医疗服务价格单独立项，是脑机接口具有示范性意义的节点：“从实验室走入临床医保，是至关重要的一环”。\n\n       此次全球首款侵入式脑机接口医疗器械获批上市，标志着中国在脑科学这一尖端领域已具备自主研发、自主临床转化的核心能力，为高位截瘫等神经功能障碍患者带来新的希望。\n', 1, 'visitor', '2026-03-14 16:43:32', '2026-03-14 16:49:06');
INSERT INTO `post` VALUES (18, '你好', '        探脑科技致力于通过脑机接口技术，为人类创造更美好的生活。我们相信，科技的终极价值在于服务人类，改善生活质量，解锁人类潜能。\n       我们需要这样的你\n- 有社会责任感的理想者 ：相信科技可以改变世界，愿意为人类福祉贡献力量\n- 跨学科合作的倡导者 ：打破学科壁垒，融合医学、工程、计算机等多领域知识\n- 创新思维的探索者 ：不断挑战现有技术边界，寻找新的解决方案\n- 坚韧不拔的实践者 ：面对挑战不退缩，持续推进项目落地\n\n       脑机接口技术不仅仅是一项科技突破，更是连接人类潜能与未来的桥梁。我们相信，每一个有梦想、有责任感的人，都能在这个领域找到自己的舞台。\n        让我们携手，用科技的力量，为更多人带来希望，为社会创造价值，共同构建一个更加包容、智慧、健康的未来！\n\n联系我们 ： 2673755910@qq.com', 1, 'visitor', '2026-03-14 16:57:36', '2026-03-14 16:57:36');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名（登录账号）',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码（建议加密存储，如BCrypt）',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'visitor' COMMENT '角色：visitor（游客）/user（普通用户）/admin（管理员）',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'offline' COMMENT '登录状态：online（在线）/offline（离线）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账号创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `idx_username`(`username`) USING BTREE,
  INDEX `idx_role`(`role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表：存储用户基础信息+身份标识' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '18380198867', '12345', 'admin', 'offline', '2026-03-09 12:59:46', '2026-03-14 17:18:31');
INSERT INTO `user` VALUES (2, '13091322869', '12345', 'admin', 'offline', '2026-03-09 13:39:32', '2026-03-14 17:19:18');

SET FOREIGN_KEY_CHECKS = 1;
