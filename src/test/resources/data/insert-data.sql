-- 插入表数据
INSERT INTO tb_dictionary_category(id, code, name, remark) VALUES (1, 'state', '状态', '');
INSERT INTO tb_dictionary_category(id, code, name, remark) VALUES (2, 'resource-type', '资源类型', '');

INSERT INTO tb_data_dictionary(id, name, remark, type, value, fk_category_id) VALUES (1, '启用', '', 'java.lang.Integer', '1', 1);
INSERT INTO tb_data_dictionary(id, name, remark, type, value, fk_category_id) VALUES (2, '禁用', '', 'java.lang.Integer', '2', 1);
INSERT INTO tb_data_dictionary(id, name, remark, type, value, fk_category_id) VALUES (3, '删除', '', 'java.lang.Integer', '3', 1);
INSERT INTO tb_data_dictionary(id, name, remark, type, value, fk_category_id) VALUES (10, '菜单类型', '', 'java.lang.Integer', '1', 2);
INSERT INTO tb_data_dictionary(id, name, remark, type, value, fk_category_id) VALUES (11, '资源类型', '', 'java.lang.Integer', '2', 2);

INSERT INTO tb_group(id, name, remark) VALUES (1, '普通用户', '');
INSERT INTO tb_group(id, name, remark) VALUES (2, '运维人员', '');
INSERT INTO tb_group(id, name, remark) VALUES (3, '超级管理员', '');

INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (1, '', '', 1, '权限管理', 1, '', null, 'glyphicon glyphicon-briefcase');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (2, 'perms[user:list]', '', 2, '用户管理', 1, '/account/user/list/**', 1, 'glyphicon glyphicon-user');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (3, 'perms[user:save],authc', '', 3, '保存用户', 2, '/account/user/save/**', 2, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (4, 'perms[user:delete],authc', '', 4, '删除用户', 2, '/account/user/delete/**', 2, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (5, 'perms[user:edit],authc', '', 5, '编辑用户', 2, '/account/user/edit/**', 2, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (6, 'perms[group:list]', '', 6, '组管理', 1, '/account/group/list/**', 1, 'glyphicon glyphicon-briefcase');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (7, 'perms[group:save],authc', '', 7, '保存组', 2, '/account/group/save/**', 6, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (8, 'perms[group:edit],authc', '', 8, '编辑组', 2, '/account/group/edit/**', 6, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (9, 'perms[group:delete],authc', '', 9, '删除组', 2, '/account/group/delete/**', 6, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (10, 'perms[resource:list]', '', 10, '资源管理', 1, '/account/resource/list/**', 1, 'glyphicon glyphicon-link');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (11, 'perms[resource:save],authc', '', 11, '保存资源', 2, '/account/resource/save/**', 10, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (12, 'perms[resource:edit],authc', '', 12, '编辑资源', 2, '/account/resource/edit/**', 10, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (13, 'perms[resource:delete],authc', '', 13, '删除资源', 2, '/account/resource/delete/**', 10, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (14, '', '', 14, '系统管理', 1, '', null, 'glyphicon glyphicon-cog');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (15, 'perms[data-dictionary:list]', '', 15, '数据字典管理', 1, '/variable/data-dictionary/list/**', 14, 'glyphicon glyphicon-list-alt');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (16, 'perms[data-dictionary:save],authc', '', 16, '保存数据字典', 2, '/variable/data-dictionary/save/**', 15, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (17, 'perms[data-dictionary:edit],authc', '', 17, '编辑数据字典', 2, '/variable/data-dictionary/edit/**', 15, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (18, 'perms[data-dictionary:delete],authc', '', 18, '删除数据字典', 2, '/variable/data-dictionary/delete/**', 15, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (19, 'perms[dictionary-category:list]', '', 19, '字典类别管理', 1, '/variable/dictionary-category/list/**', 14, 'glyphicon glyphicon-folder-close');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (20, 'perms[dictionary-category:save],authc', '', 20, '保存字典类别', 2, '/variable/dictionary-category/save/**', 19, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (21, 'perms[dictionary-category:edit],authc', '', 21, '编辑字典类别', 2, '/variable/dictionary-category/edit/**', 19, '');
INSERT INTO tb_resource(id, permission, remark, sort, name, type, value, fk_parent_id, icon) VALUES (22, 'perms[dictionary-category:delete],authc', '', 22, '删除字典类别', 2, '/variable/dictionary-category/delete/**', 19, '');

INSERT INTO tb_user (id, email, password, nickname, state, username) VALUES (3, 'maintain@baseframework.com', 'e10adc3949ba59abbe56e057f20f883e', '运维用户', 1, 'maintain');
INSERT INTO tb_user (id, email, password, nickname, state, username) VALUES (4, 'user@baseframework.com', 'e10adc3949ba59abbe56e057f20f883e', '普通用户', 1, 'user');
INSERT INTO tb_user (id, email, password, nickname, state, username) VALUES (5, 'administrator@baseframework.com', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', 1, 'admin');

INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (1, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (2, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (6, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (7, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (9, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (11, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (13, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (15, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (16, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (18, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (20, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (22, 1);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (15, 2);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (16, 2);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (17, 2);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (18, 2);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (19, 2);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (20, 2);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (21, 2);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (22, 2);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (1, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (2, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (3, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (4, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (5, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (6, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (7, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (8, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (9, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (10, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (11, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (12, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (13, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (14, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (15, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (16, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (17, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (18, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (19, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (20, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (21, 3);
INSERT INTO tb_group_resource (fk_resource_id, fk_group_id) VALUES (22, 3);

INSERT INTO tb_group_user (fk_group_id, fk_user_id) VALUES (2, 3);
INSERT INTO tb_group_user (fk_group_id, fk_user_id) VALUES (1, 4);
INSERT INTO tb_group_user (fk_group_id, fk_user_id) VALUES (3, 5);