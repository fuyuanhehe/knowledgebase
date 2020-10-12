/*
 Navicat Premium Data Transfer

 Source Server         : 47.110.76.241
 Source Server Type    : PostgreSQL
 Source Server Version : 100004
 Source Host           : 47.110.76.241:5432
 Source Catalog        : knowledge_base
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100004
 File Encoding         : 65001

 Date: 08/05/2020 18:07:45
*/


-- ----------------------------
-- Sequence structure for act_evt_log_log_nr__seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."act_evt_log_log_nr__seq";
CREATE SEQUENCE "public"."act_evt_log_log_nr__seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for base_circle_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."base_circle_id_seq";
CREATE SEQUENCE "public"."base_circle_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for base_curriculum_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."base_curriculum_id_seq";
CREATE SEQUENCE "public"."base_curriculum_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for base_document_category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."base_document_category_id_seq";
CREATE SEQUENCE "public"."base_document_category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for base_document_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."base_document_id_seq";
CREATE SEQUENCE "public"."base_document_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for base_subject_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."base_subject_id_seq";
CREATE SEQUENCE "public"."base_subject_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for base_test paper_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."base_test paper_id_seq";
CREATE SEQUENCE "public"."base_test paper_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for bus_project_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bus_project_id_seq";
CREATE SEQUENCE "public"."bus_project_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sys_dept_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_dept_id_seq";
CREATE SEQUENCE "public"."sys_dept_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sys_dict_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_dict_id_seq";
CREATE SEQUENCE "public"."sys_dict_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sys_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_log_id_seq";
CREATE SEQUENCE "public"."sys_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sys_power_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_power_id_seq";
CREATE SEQUENCE "public"."sys_power_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sys_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_role_id_seq";
CREATE SEQUENCE "public"."sys_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sys_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_user_id_seq";
CREATE SEQUENCE "public"."sys_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for act_evt_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_evt_log";
CREATE TABLE "public"."act_evt_log" (
  "log_nr_" int4 NOT NULL DEFAULT nextval('act_evt_log_log_nr__seq'::regclass),
  "type_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "time_stamp_" timestamp(6) NOT NULL,
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "data_" bytea,
  "lock_owner_" varchar(255) COLLATE "pg_catalog"."default",
  "lock_time_" timestamp(6),
  "is_processed_" int2 DEFAULT 0
)
;

-- ----------------------------
-- Table structure for act_ge_bytearray
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ge_bytearray";
CREATE TABLE "public"."act_ge_bytearray" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "deployment_id_" varchar(64) COLLATE "pg_catalog"."default",
  "bytes_" bytea,
  "generated_" bool
)
;

-- ----------------------------
-- Table structure for act_ge_property
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ge_property";
CREATE TABLE "public"."act_ge_property" (
  "name_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "value_" varchar(300) COLLATE "pg_catalog"."default",
  "rev_" int4
)
;

-- ----------------------------
-- Records of act_ge_property
-- ----------------------------
INSERT INTO "public"."act_ge_property" VALUES ('schema.version', '6.0.0.4', 1);
INSERT INTO "public"."act_ge_property" VALUES ('schema.history', 'create(6.0.0.4)', 1);
INSERT INTO "public"."act_ge_property" VALUES ('next.dbid', '1', 1);
INSERT INTO "public"."act_ge_property" VALUES ('cfg.execution-related-entities-count', 'false', 1);

-- ----------------------------
-- Table structure for act_hi_actinst
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_actinst";
CREATE TABLE "public"."act_hi_actinst" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "act_id_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "call_proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "act_name_" varchar(255) COLLATE "pg_catalog"."default",
  "act_type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "assignee_" varchar(255) COLLATE "pg_catalog"."default",
  "start_time_" timestamp(6) NOT NULL,
  "end_time_" timestamp(6),
  "duration_" int8,
  "delete_reason_" varchar(4000) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_hi_attachment
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_attachment";
CREATE TABLE "public"."act_hi_attachment" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "description_" varchar(4000) COLLATE "pg_catalog"."default",
  "type_" varchar(255) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "url_" varchar(4000) COLLATE "pg_catalog"."default",
  "content_id_" varchar(64) COLLATE "pg_catalog"."default",
  "time_" timestamp(6)
)
;

-- ----------------------------
-- Table structure for act_hi_comment
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_comment";
CREATE TABLE "public"."act_hi_comment" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "type_" varchar(255) COLLATE "pg_catalog"."default",
  "time_" timestamp(6) NOT NULL,
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "action_" varchar(255) COLLATE "pg_catalog"."default",
  "message_" varchar(4000) COLLATE "pg_catalog"."default",
  "full_msg_" bytea
)
;

-- ----------------------------
-- Table structure for act_hi_detail
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_detail";
CREATE TABLE "public"."act_hi_detail" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "act_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "var_type_" varchar(64) COLLATE "pg_catalog"."default",
  "rev_" int4,
  "time_" timestamp(6) NOT NULL,
  "bytearray_id_" varchar(64) COLLATE "pg_catalog"."default",
  "double_" float8,
  "long_" int8,
  "text_" varchar(4000) COLLATE "pg_catalog"."default",
  "text2_" varchar(4000) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_hi_identitylink
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_identitylink";
CREATE TABLE "public"."act_hi_identitylink" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "group_id_" varchar(255) COLLATE "pg_catalog"."default",
  "type_" varchar(255) COLLATE "pg_catalog"."default",
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_hi_procinst
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_procinst";
CREATE TABLE "public"."act_hi_procinst" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "business_key_" varchar(255) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "start_time_" timestamp(6) NOT NULL,
  "end_time_" timestamp(6),
  "duration_" int8,
  "start_user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "start_act_id_" varchar(255) COLLATE "pg_catalog"."default",
  "end_act_id_" varchar(255) COLLATE "pg_catalog"."default",
  "super_process_instance_id_" varchar(64) COLLATE "pg_catalog"."default",
  "delete_reason_" varchar(4000) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "name_" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_hi_taskinst
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_taskinst";
CREATE TABLE "public"."act_hi_taskinst" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_def_key_" varchar(255) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "parent_task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "description_" varchar(4000) COLLATE "pg_catalog"."default",
  "owner_" varchar(255) COLLATE "pg_catalog"."default",
  "assignee_" varchar(255) COLLATE "pg_catalog"."default",
  "start_time_" timestamp(6) NOT NULL,
  "claim_time_" timestamp(6),
  "end_time_" timestamp(6),
  "duration_" int8,
  "delete_reason_" varchar(4000) COLLATE "pg_catalog"."default",
  "priority_" int4,
  "due_date_" timestamp(6),
  "form_key_" varchar(255) COLLATE "pg_catalog"."default",
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_hi_varinst
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_varinst";
CREATE TABLE "public"."act_hi_varinst" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "var_type_" varchar(100) COLLATE "pg_catalog"."default",
  "rev_" int4,
  "bytearray_id_" varchar(64) COLLATE "pg_catalog"."default",
  "double_" float8,
  "long_" int8,
  "text_" varchar(4000) COLLATE "pg_catalog"."default",
  "text2_" varchar(4000) COLLATE "pg_catalog"."default",
  "create_time_" timestamp(6),
  "last_updated_time_" timestamp(6)
)
;

-- ----------------------------
-- Table structure for act_id_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_id_group";
CREATE TABLE "public"."act_id_group" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "type_" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_id_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_id_info";
CREATE TABLE "public"."act_id_info" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "user_id_" varchar(64) COLLATE "pg_catalog"."default",
  "type_" varchar(64) COLLATE "pg_catalog"."default",
  "key_" varchar(255) COLLATE "pg_catalog"."default",
  "value_" varchar(255) COLLATE "pg_catalog"."default",
  "password_" bytea,
  "parent_id_" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_id_membership
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_id_membership";
CREATE TABLE "public"."act_id_membership" (
  "user_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "group_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for act_id_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_id_user";
CREATE TABLE "public"."act_id_user" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "first_" varchar(255) COLLATE "pg_catalog"."default",
  "last_" varchar(255) COLLATE "pg_catalog"."default",
  "email_" varchar(255) COLLATE "pg_catalog"."default",
  "pwd_" varchar(255) COLLATE "pg_catalog"."default",
  "picture_id_" varchar(64) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_procdef_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_procdef_info";
CREATE TABLE "public"."act_procdef_info" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "info_json_id_" varchar(64) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_re_deployment
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_re_deployment";
CREATE TABLE "public"."act_re_deployment" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "key_" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "deploy_time_" timestamp(6),
  "engine_version_" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_re_model
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_re_model";
CREATE TABLE "public"."act_re_model" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "key_" varchar(255) COLLATE "pg_catalog"."default",
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "create_time_" timestamp(6),
  "last_update_time_" timestamp(6),
  "version_" int4,
  "meta_info_" varchar(4000) COLLATE "pg_catalog"."default",
  "deployment_id_" varchar(64) COLLATE "pg_catalog"."default",
  "editor_source_value_id_" varchar(64) COLLATE "pg_catalog"."default",
  "editor_source_extra_value_id_" varchar(64) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_re_procdef
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_re_procdef";
CREATE TABLE "public"."act_re_procdef" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "key_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "version_" int4 NOT NULL,
  "deployment_id_" varchar(64) COLLATE "pg_catalog"."default",
  "resource_name_" varchar(4000) COLLATE "pg_catalog"."default",
  "dgrm_resource_name_" varchar(4000) COLLATE "pg_catalog"."default",
  "description_" varchar(4000) COLLATE "pg_catalog"."default",
  "has_start_form_key_" bool,
  "has_graphical_notation_" bool,
  "suspension_state_" int4,
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "engine_version_" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_ru_deadletter_job
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_deadletter_job";
CREATE TABLE "public"."act_ru_deadletter_job" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "exclusive_" bool,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "process_instance_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "exception_stack_id_" varchar(64) COLLATE "pg_catalog"."default",
  "exception_msg_" varchar(4000) COLLATE "pg_catalog"."default",
  "duedate_" timestamp(6),
  "repeat_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_type_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_cfg_" varchar(4000) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_ru_event_subscr
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_event_subscr";
CREATE TABLE "public"."act_ru_event_subscr" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "event_type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "event_name_" varchar(255) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "activity_id_" varchar(64) COLLATE "pg_catalog"."default",
  "configuration_" varchar(255) COLLATE "pg_catalog"."default",
  "created_" timestamp(6) NOT NULL,
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_ru_execution
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_execution";
CREATE TABLE "public"."act_ru_execution" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "business_key_" varchar(255) COLLATE "pg_catalog"."default",
  "parent_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "super_exec_" varchar(64) COLLATE "pg_catalog"."default",
  "root_proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "act_id_" varchar(255) COLLATE "pg_catalog"."default",
  "is_active_" bool,
  "is_concurrent_" bool,
  "is_scope_" bool,
  "is_event_scope_" bool,
  "is_mi_root_" bool,
  "suspension_state_" int4,
  "cached_ent_state_" int4,
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "start_time_" timestamp(6),
  "start_user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "lock_time_" timestamp(6),
  "is_count_enabled_" bool,
  "evt_subscr_count_" int4,
  "task_count_" int4,
  "job_count_" int4,
  "timer_job_count_" int4,
  "susp_job_count_" int4,
  "deadletter_job_count_" int4,
  "var_count_" int4,
  "id_link_count_" int4
)
;

-- ----------------------------
-- Table structure for act_ru_identitylink
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_identitylink";
CREATE TABLE "public"."act_ru_identitylink" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "group_id_" varchar(255) COLLATE "pg_catalog"."default",
  "type_" varchar(255) COLLATE "pg_catalog"."default",
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_ru_job
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_job";
CREATE TABLE "public"."act_ru_job" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "lock_exp_time_" timestamp(6),
  "lock_owner_" varchar(255) COLLATE "pg_catalog"."default",
  "exclusive_" bool,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "process_instance_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "retries_" int4,
  "exception_stack_id_" varchar(64) COLLATE "pg_catalog"."default",
  "exception_msg_" varchar(4000) COLLATE "pg_catalog"."default",
  "duedate_" timestamp(6),
  "repeat_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_type_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_cfg_" varchar(4000) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_ru_suspended_job
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_suspended_job";
CREATE TABLE "public"."act_ru_suspended_job" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "exclusive_" bool,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "process_instance_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "retries_" int4,
  "exception_stack_id_" varchar(64) COLLATE "pg_catalog"."default",
  "exception_msg_" varchar(4000) COLLATE "pg_catalog"."default",
  "duedate_" timestamp(6),
  "repeat_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_type_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_cfg_" varchar(4000) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_ru_task
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_task";
CREATE TABLE "public"."act_ru_task" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "parent_task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "description_" varchar(4000) COLLATE "pg_catalog"."default",
  "task_def_key_" varchar(255) COLLATE "pg_catalog"."default",
  "owner_" varchar(255) COLLATE "pg_catalog"."default",
  "assignee_" varchar(255) COLLATE "pg_catalog"."default",
  "delegation_" varchar(64) COLLATE "pg_catalog"."default",
  "priority_" int4,
  "create_time_" timestamp(6),
  "due_date_" timestamp(6),
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "suspension_state_" int4,
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "form_key_" varchar(255) COLLATE "pg_catalog"."default",
  "claim_time_" timestamp(6)
)
;

-- ----------------------------
-- Table structure for act_ru_timer_job
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_timer_job";
CREATE TABLE "public"."act_ru_timer_job" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "lock_exp_time_" timestamp(6),
  "lock_owner_" varchar(255) COLLATE "pg_catalog"."default",
  "exclusive_" bool,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "process_instance_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "retries_" int4,
  "exception_stack_id_" varchar(64) COLLATE "pg_catalog"."default",
  "exception_msg_" varchar(4000) COLLATE "pg_catalog"."default",
  "duedate_" timestamp(6),
  "repeat_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_type_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_cfg_" varchar(4000) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_ru_variable
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_variable";
CREATE TABLE "public"."act_ru_variable" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "name_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "bytearray_id_" varchar(64) COLLATE "pg_catalog"."default",
  "double_" float8,
  "long_" int8,
  "text_" varchar(4000) COLLATE "pg_catalog"."default",
  "text2_" varchar(4000) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for base_circle
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_circle";
CREATE TABLE "public"."base_circle" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(50) COLLATE "pg_catalog"."default",
  "reserve1" varchar(30) COLLATE "pg_catalog"."default",
  "reserve2" varchar(30) COLLATE "pg_catalog"."default",
  "reserve3" varchar(30) COLLATE "pg_catalog"."default",
  "reserve4" varchar(30) COLLATE "pg_catalog"."default",
  "reserve5" varchar(30) COLLATE "pg_catalog"."default",
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."base_circle"."id" IS '圈子id';
COMMENT ON COLUMN "public"."base_circle"."name" IS '圈子名称';
COMMENT ON COLUMN "public"."base_circle"."reserve1" IS '预留字段1';
COMMENT ON COLUMN "public"."base_circle"."reserve2" IS '预留字段2';
COMMENT ON COLUMN "public"."base_circle"."reserve3" IS '预留字段3';
COMMENT ON COLUMN "public"."base_circle"."reserve4" IS '预留字段4';
COMMENT ON COLUMN "public"."base_circle"."reserve5" IS '预留字段5';
COMMENT ON COLUMN "public"."base_circle"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_circle"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_circle"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."base_circle"."update_time" IS '修改时间';
COMMENT ON TABLE "public"."base_circle" IS '圈子类别';

-- ----------------------------
-- Records of base_circle
-- ----------------------------
INSERT INTO "public"."base_circle" VALUES ('9add2f57b7a64163a0a063cfc9f6fcb7', '测试圈1', NULL, NULL, NULL, NULL, NULL, 'root', '2020-04-03 10:12:32.523532', NULL, NULL);
INSERT INTO "public"."base_circle" VALUES ('7c19976e01b5483a8c257c0fb3718b89', '测试圈2', NULL, NULL, NULL, NULL, NULL, 'root', '2020-04-03 14:08:15.208446', NULL, NULL);

-- ----------------------------
-- Table structure for base_curriculum
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_curriculum";
CREATE TABLE "public"."base_curriculum" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(90) COLLATE "pg_catalog"."default",
  "cover_url" varchar(500) COLLATE "pg_catalog"."default",
  "introduction" text COLLATE "pg_catalog"."default",
  "resources" jsonb,
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "learn_begin_time" date,
  "learn_end_time" date,
  "status" int2 DEFAULT 0
)
;
COMMENT ON COLUMN "public"."base_curriculum"."id" IS '课程id';
COMMENT ON COLUMN "public"."base_curriculum"."name" IS '课程名称';
COMMENT ON COLUMN "public"."base_curriculum"."cover_url" IS '课程封面地址';
COMMENT ON COLUMN "public"."base_curriculum"."introduction" IS '课程简介';
COMMENT ON COLUMN "public"."base_curriculum"."resources" IS '课程资源（json）';
COMMENT ON COLUMN "public"."base_curriculum"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_curriculum"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_curriculum"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."base_curriculum"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."base_curriculum"."learn_begin_time" IS '学习开始时间';
COMMENT ON COLUMN "public"."base_curriculum"."learn_end_time" IS '学习结束时间';
COMMENT ON COLUMN "public"."base_curriculum"."status" IS '学习状态（0：未开始；1：学习中；2：已结束）';
COMMENT ON TABLE "public"."base_curriculum" IS '课程表';

-- ----------------------------
-- Records of base_curriculum
-- ----------------------------
INSERT INTO "public"."base_curriculum" VALUES ('1a0ca99c3f694467876429b57824c453', 'dfdfds', '/file/2020-03-25/20200325035857-d790092636798302a70ebff9a852bdb6.jpg', '的点点滴滴', '[{"data": [], "videoUrl": [{"url": "/file/2020-03-25/20200325035915-8b1481602e20c4357f5ecaca95c7325f.mp4", "name": "8b1481602e20c4357f5ecaca95c7325f.mp4"}]}]', 'root', '2020-03-25 15:59:16.38394', NULL, NULL, '2020-03-25', '2020-03-31', 0);
INSERT INTO "public"."base_curriculum" VALUES ('483d1e4161924082904e77d1a2388c02', '测试0326', '/file/2020-04-15/20200415030140-d790092636798302a70ebff9a852bdb6.jpg', '测试0326', '[{"data": [], "videoUrl": [{"url": "/file/2020-04-15/20200415042120-8b1481602e20c4357f5ecaca95c7325f.mp4", "name": "8b1481602e20c4357f5ecaca95c7325f.mp4"}]}, {"data": [{"url": "/file/2020-04-16/20200416111419-download (1).zip", "name": "download (1).zip"}], "videoUrl": [{"url": "/file/2020-04-16/20200416111256-和贯科技-2018工作总结和2019年工作计划（模版）.pptx", "name": "和贯科技-2018工作总结和2019年工作计划（模版）.pptx"}]}]', 'root', '2020-03-26 12:47:59.582484', 'root', '2020-04-16 11:14:22.324', '2020-03-30', '2020-03-31', 1);
INSERT INTO "public"."base_curriculum" VALUES ('956fa43206314502b69d5289514334a6', '测试1', '/file/2020-04-15/20200415110904-13287978_102304113000_2.jpg', '测试1', '[{"data": [{"url": "/file/2020-03-25/20200325034954-download (1).zip", "name": "download (1).zip"}], "videoUrl": [{"url": "/file/2020-03-25/20200325034949-8b1481602e20c4357f5ecaca95c7325f.mp4", "name": "8b1481602e20c4357f5ecaca95c7325f.mp4"}]}, {"data": [{"url": "/file/2020-03-25/20200325035146-download.zip", "name": "download.zip"}], "videoUrl": [{"url": "/file/2020-03-25/20200325035141-8b1481602e20c4357f5ecaca95c7325f.mp4", "name": "8b1481602e20c4357f5ecaca95c7325f.mp4"}]}]', 'root', '2020-03-25 15:49:55.291174', 'root', '2020-04-15 11:09:08.38', '2020-03-17', '2020-03-31', 2);

-- ----------------------------
-- Table structure for base_document
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_document";
CREATE TABLE "public"."base_document" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default",
  "type" int2 DEFAULT 0,
  "size" numeric(8,2),
  "suffix" varchar(10) COLLATE "pg_catalog"."default",
  "pid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT '0'::character varying,
  "weight" int2 DEFAULT 0,
  "url" varchar(1000) COLLATE "pg_catalog"."default",
  "category_id" varchar(32) COLLATE "pg_catalog"."default",
  "dept_id" varchar(32) COLLATE "pg_catalog"."default",
  "cover_url" varchar(300) COLLATE "pg_catalog"."default",
  "cover_size" numeric(8,2),
  "browse_num" int4 DEFAULT 0,
  "comment_num" int4 DEFAULT 0,
  "download_num" int4 DEFAULT 0,
  "udpate_num" int4 DEFAULT 0,
  "print_num" int4 DEFAULT 0,
  "correction_num" int4 DEFAULT 0,
  "reserve1" varchar(30) COLLATE "pg_catalog"."default",
  "reserve2" varchar(4000) COLLATE "pg_catalog"."default",
  "reserve3" varchar(30) COLLATE "pg_catalog"."default",
  "reserve4" varchar(50) COLLATE "pg_catalog"."default",
  "project_id" varchar(50) COLLATE "pg_catalog"."default",
  "create_user" varchar(50) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."base_document"."id" IS '文档id';
COMMENT ON COLUMN "public"."base_document"."name" IS '文档名称';
COMMENT ON COLUMN "public"."base_document"."type" IS '文档类型（0：文件夹；1：文件）';
COMMENT ON COLUMN "public"."base_document"."size" IS '文档大小（bit）';
COMMENT ON COLUMN "public"."base_document"."suffix" IS '文档后缀';
COMMENT ON COLUMN "public"."base_document"."pid" IS '文档父id';
COMMENT ON COLUMN "public"."base_document"."weight" IS '文档权重（0：公司文档；1：部门文档；2：个人文档;3: 知识文档）';
COMMENT ON COLUMN "public"."base_document"."url" IS '文档地址';
COMMENT ON COLUMN "public"."base_document"."category_id" IS '文档类别id';
COMMENT ON COLUMN "public"."base_document"."dept_id" IS '创建人部门id';
COMMENT ON COLUMN "public"."base_document"."cover_url" IS '文档封面地址';
COMMENT ON COLUMN "public"."base_document"."cover_size" IS '封面大小';
COMMENT ON COLUMN "public"."base_document"."browse_num" IS '浏览量';
COMMENT ON COLUMN "public"."base_document"."comment_num" IS '评论数';
COMMENT ON COLUMN "public"."base_document"."download_num" IS '下载数';
COMMENT ON COLUMN "public"."base_document"."udpate_num" IS '修改数';
COMMENT ON COLUMN "public"."base_document"."print_num" IS '打印数';
COMMENT ON COLUMN "public"."base_document"."correction_num" IS '改正记录数';
COMMENT ON COLUMN "public"."base_document"."reserve1" IS '文档预留1';
COMMENT ON COLUMN "public"."base_document"."reserve2" IS '动态图正文';
COMMENT ON COLUMN "public"."base_document"."reserve3" IS '是否精品文档(0:yes   1: no)';
COMMENT ON COLUMN "public"."base_document"."reserve4" IS '文档预留4';
COMMENT ON COLUMN "public"."base_document"."project_id" IS '项目id';
COMMENT ON COLUMN "public"."base_document"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_document"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_document"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."base_document"."update_time" IS '修改时间';
COMMENT ON TABLE "public"."base_document" IS '文档表';

-- ----------------------------
-- Records of base_document
-- ----------------------------
INSERT INTO "public"."base_document" VALUES ('b298d6aca3fe4205b1b65319b2dfe835', '20200327055000-用户列表 (1).xls', 1, NULL, NULL, '0', 5, '/file/2020-03-31/20200331033513-20200327055000-用户列表 (1).xls', 'e34b8eb18de243808385baab6419c21f', '1', NULL, NULL, 9, 6, 1, 1, 0, 1, NULL, NULL, NULL, NULL, '3de6fefe32e742d4878b9d0d4c837612', 'root', '2020-03-31 15:35:16.036', 'root', '2020-04-24 09:42:22.894');
INSERT INTO "public"."base_document" VALUES ('c2e6862baf9c4a54ad896bdd5aae1858', '新建123文件夹1', 0, NULL, NULL, '0', 5, NULL, '123123', '1', NULL, NULL, 1, 5, 1, 0, 0, 0, NULL, NULL, '1', NULL, '3de6fefe32e742d4878b9d0d4c837612', 'root', '2020-01-01 14:49:28', 'root', '2020-02-14 15:08:19');
INSERT INTO "public"."base_document" VALUES ('a326f5f1a2f54432b9ceff04fc6de38e', 'asd123', 0, NULL, NULL, '3450b907ee654ae7867a9570bccc6b73', 0, NULL, 'e34b8eb18de243808385baab6419c21f', '1', NULL, NULL, 5, 0, 22, 0, 0, 0, NULL, NULL, '1', NULL, NULL, 'root', '2020-04-27 15:07:41.407', 'root', '2020-04-27 15:07:49.166');
INSERT INTO "public"."base_document" VALUES ('3f1587a09e8f45598ae4b876e1d8771b', '新建文件夹7', 0, NULL, NULL, '98dbca7ed1e14ac08e75fee0c227f9cc', 2, NULL, '123123', '1', NULL, NULL, 1, 0, 0, 0, 0, 0, NULL, NULL, '1', NULL, NULL, 'root', '2020-04-27 17:37:24.378', 'root', '2020-04-27 17:37:28.39');
INSERT INTO "public"."base_document" VALUES ('218e4b0fc71141be89e1f96a0867664e', '20200327055000-用户列表 (1).xls', 1, NULL, NULL, '0', 5, '/file/2020-03-31/20200331045148-20200327055000-用户列表 (1).xls', '123123', NULL, '/file/2020-04-02/20200402032631-d790092636798302a70ebff9a852bdb6.jpg', NULL, 22, 0, 3, 0, 0, 0, NULL, NULL, '0', NULL, '3de6fefe32e742d4878b9d0d4c837612', 'root', '2020-03-31 16:51:47.873', 'root', '2020-04-02 15:26:32.918');
INSERT INTO "public"."base_document" VALUES ('cda75646485d427da9d323c1627f25b0', '新建文件夹6', 0, NULL, NULL, '0', 0, NULL, 'e34b8eb18de243808385baab6419c21f', '1', NULL, NULL, 6, 0, 0, 0, 0, 0, NULL, NULL, '1', NULL, NULL, 'root', '2020-04-28 15:11:37', NULL, '2020-04-27 15:11:37.435');
INSERT INTO "public"."base_document" VALUES ('046a4df2456946889e550db00a028477', '20200327055000-用户列表.xls', 1, NULL, NULL, '0', 5, '/file/2020-03-31/20200331032402-20200327055000-用户列表.xls', '123123', NULL, '/file/2020-04-02/20200402025046-13287978_102304113000_2.jpg', NULL, 6, 1, 11, 0, 0, 0, NULL, NULL, '0', NULL, '3de6fefe32e742d4878b9d0d4c837612', 'root', '2020-03-31 15:24:02.526', 'root', '2020-02-14 14:50:47');
INSERT INTO "public"."base_document" VALUES ('98dbca7ed1e14ac08e75fee0c227f9cc', '新建文件夹5', 0, NULL, NULL, '0', 0, NULL, '123123', '1', NULL, NULL, 2, 0, 1, 0, 0, 0, NULL, NULL, '1', NULL, NULL, 'root', '2020-04-27 15:10:07.848', 'root', '2020-04-27 15:10:12.34');
INSERT INTO "public"."base_document" VALUES ('c09b372892404cc882611e52e560f8fd', '20200331032402-20200327055000-用户列表.xls', 1, NULL, NULL, '0', 5, '/file/2020-03-31/20200331051042-20200331032402-20200327055000-用户列表.xls', 'e34b8eb18de243808385baab6419c21f', NULL, '/file/2020-04-02/20200402030235-ccc.jpg', NULL, 8, 0, 66, 0, 0, 0, NULL, NULL, '0', NULL, '3de6fefe32e742d4878b9d0d4c837612', 'root', '2020-04-28 17:10:42', 'root', '2020-04-02 15:02:36.323');
INSERT INTO "public"."base_document" VALUES ('1b051f4ef9024ab89b1ea9e9d49affad', '测试-候选人模板.xls', 1, NULL, NULL, '98dbca7ed1e14ac08e75fee0c227f9cc', 2, '/file/2020-04-27/20200427053743-测试-候选人模板.xls', '123123', NULL, NULL, NULL, 8, NULL, 2, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, 'root', '2020-04-27 17:37:44.09', NULL, '2020-04-22 10:56:33');
INSERT INTO "public"."base_document" VALUES ('5a01cfd9fa234c4ab76450feb64cfbf8', '文档1', 0, NULL, NULL, 'f41ed2aed0074e7582654efe6bc088ca', 2, NULL, 'e34b8eb18de243808385baab6419c21f', '1', NULL, NULL, 0, 0, 111, 0, 0, 0, NULL, NULL, '1', NULL, NULL, 'root', '2020-05-06 09:48:31.13', 'root', '2020-05-06 09:48:37.168');
INSERT INTO "public"."base_document" VALUES ('f41ed2aed0074e7582654efe6bc088ca', '测试文档', 0, NULL, NULL, '0', 2, NULL, 'e34b8eb18de243808385baab6419c21f', '1', NULL, NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, '1', NULL, NULL, 'root', '2020-05-06 09:48:13.327', 'root', '2020-05-06 09:48:21.408');
INSERT INTO "public"."base_document" VALUES ('4d6add7d90df4a298bc4f610908489e9', '新建文aa件夹7', 0, NULL, NULL, 'a53c94cbf7094d659fdcafce29f78330', 5, NULL, '123123', '1', NULL, NULL, 3, 0, 0, 0, 0, 0, NULL, NULL, '1', NULL, '3de6fefe32e742d4878b9d0d4c837612', 'root', '2020-04-27 15:17:11.872', 'root', '2020-04-27 15:17:17.323');
INSERT INTO "public"."base_document" VALUES ('a433b57d6734421093cc262ecfe0afe7', 'document.pdf', 1, NULL, NULL, '0', 5, '/file/2020-03-31/20200331024742-document.pdf', '22asd', NULL, '/file/2020-03-31/20200331030209-18063302_210604412116_2.jpg', NULL, 1, 0, 0, 0, 0, 0, NULL, NULL, '0', NULL, '3de6fefe32e742d4878b9d0d4c837612', 'root', '2020-03-31 14:50:22.776', 'root', '2020-03-31 15:04:50.865');
INSERT INTO "public"."base_document" VALUES ('a53c94cbf7094d659fdcafce29f78330', '新建文件ffyy夹7', 0, NULL, NULL, '0', 5, NULL, '123123', '1', NULL, NULL, 1, 0, 0, 0, 0, 0, NULL, NULL, '1', NULL, '3de6fefe32e742d4878b9d0d4c837612', 'root', '2020-04-27 15:16:48.448', 'root', '2020-04-27 15:16:56.545');
INSERT INTO "public"."base_document" VALUES ('7d451763b7e24ba38a29b0e16a66f4f4', '20200327055000-用户列表 (1).xls', 1, NULL, NULL, 'c2e6862baf9c4a54ad896bdd5aae1858', 5, '/file/2020-03-31/20200331024943-20200327055000-用户列表 (1).xls', '22asd', NULL, NULL, NULL, 33, 22, 22, 0, 0, 0, NULL, NULL, '1', NULL, '3de6fefe32e742d4878b9d0d4c837612', 'root', '2020-03-31 14:52:23.699', NULL, '2020-04-28 10:56:31');

-- ----------------------------
-- Table structure for base_document_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_document_category";
CREATE TABLE "public"."base_document_category" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "url" varchar(500) COLLATE "pg_catalog"."default",
  "dept_id" varchar(32) COLLATE "pg_catalog"."default",
  "addroval_time" timestamp(6),
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6),
  "status" int2 DEFAULT 0,
  "approval_opinion" varchar(100) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."base_document_category"."id" IS '文档类别id';
COMMENT ON COLUMN "public"."base_document_category"."name" IS '文档类别名称';
COMMENT ON COLUMN "public"."base_document_category"."url" IS 'url';
COMMENT ON COLUMN "public"."base_document_category"."dept_id" IS '所属部门id';
COMMENT ON COLUMN "public"."base_document_category"."addroval_time" IS '审批时间';
COMMENT ON COLUMN "public"."base_document_category"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_document_category"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."base_document_category"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_document_category"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."base_document_category"."status" IS '审批状态';
COMMENT ON COLUMN "public"."base_document_category"."approval_opinion" IS '审批意见';
COMMENT ON TABLE "public"."base_document_category" IS '文档类别';

-- ----------------------------
-- Records of base_document_category
-- ----------------------------
INSERT INTO "public"."base_document_category" VALUES ('e34b8eb18de243808385baab6419c21f', '测试1', NULL, '1', NULL, 'root', NULL, '2020-03-30 14:37:17.745', NULL, 0, NULL);
INSERT INTO "public"."base_document_category" VALUES ('123123', '测试22', NULL, NULL, NULL, NULL, NULL, '2020-04-03 01:36:52.328524', NULL, 0, NULL);
INSERT INTO "public"."base_document_category" VALUES ('22asd', '分类3', NULL, NULL, NULL, NULL, NULL, '2020-05-07 06:38:24.902201', NULL, 0, NULL);

-- ----------------------------
-- Table structure for base_exam
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_exam";
CREATE TABLE "public"."base_exam" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(90) COLLATE "pg_catalog"."default",
  "test_paper_id" varchar(32) COLLATE "pg_catalog"."default",
  "begin_time" timestamp(6),
  "end_time" timestamp(6),
  "status" int2 DEFAULT 0,
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "answer_time" int4
)
;
COMMENT ON COLUMN "public"."base_exam"."id" IS '考试id';
COMMENT ON COLUMN "public"."base_exam"."name" IS '考试名称';
COMMENT ON COLUMN "public"."base_exam"."test_paper_id" IS '试卷id';
COMMENT ON COLUMN "public"."base_exam"."begin_time" IS '考试开始时间';
COMMENT ON COLUMN "public"."base_exam"."end_time" IS '考试结束时间';
COMMENT ON COLUMN "public"."base_exam"."status" IS '考试状态（0：未开始；1：进行中；2：已结束）';
COMMENT ON COLUMN "public"."base_exam"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_exam"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_exam"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."base_exam"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."base_exam"."answer_time" IS '答题时间';
COMMENT ON TABLE "public"."base_exam" IS '考试表';

-- ----------------------------
-- Records of base_exam
-- ----------------------------
INSERT INTO "public"."base_exam" VALUES ('4960d8730cd94d4392182986ff5311f0', '测试xxxxx116', '1', '2017-11-11 11:11:11', '2017-11-11 11:11:11', NULL, 'anonymousUser', '2020-04-15 09:32:59.25199', NULL, NULL, 10);
INSERT INTO "public"."base_exam" VALUES ('27461b0d490347b09c3d35d4d481f29c', '测试考试0415-3', 'fae080e25230452faf24b289146b6c88', '2020-04-17 00:00:00', '2020-04-30 00:00:00', NULL, 'root', '2020-04-15 10:45:50.563197', 'root', '2020-04-16 16:56:00.297', 60);
INSERT INTO "public"."base_exam" VALUES ('65d40be412e64cb5aa9d774de0888e76', '测试考试0415-2', '972c142373294d06ae64eb6982ecfc32', '2020-04-18 00:00:00', '2020-04-21 00:00:00', NULL, 'root', '2020-04-15 09:39:57.785465', 'root', '2020-04-16 16:56:09.443', 60);
INSERT INTO "public"."base_exam" VALUES ('0f1a751b926f4af495fe38006d45d559', NULL, NULL, NULL, NULL, NULL, 'root', '2020-04-20 16:11:52.035499', NULL, NULL, NULL);
INSERT INTO "public"."base_exam" VALUES ('784f534cc67f4f38bfbe6e4ccc47afd5', NULL, NULL, NULL, NULL, NULL, 'root', '2020-04-20 17:11:29.533207', NULL, NULL, NULL);
INSERT INTO "public"."base_exam" VALUES ('f680ad5a6eaa4ca882c305e04decd8ac', '考试测试0422-1', 'fae080e25230452faf24b289146b6c88', '2020-04-23 00:00:00', '2020-04-30 00:00:00', NULL, 'root', '2020-04-22 11:40:01.512134', NULL, NULL, 30);
INSERT INTO "public"."base_exam" VALUES ('abae293d472f40338dac2450848317e3', '考试测试0422-2', '972c142373294d06ae64eb6982ecfc32', '2020-04-24 14:30:00', '2020-04-29 18:00:00', NULL, 'root', '2020-04-22 12:50:40.82013', NULL, NULL, 20);
INSERT INTO "public"."base_exam" VALUES ('0e8301c4766b491d9fe85169194340a7', '测试考试0415-1', '972c142373294d06ae64eb6982ecfc32', '2020-04-16 00:00:00', '2020-04-23 00:00:00', NULL, 'root', '2020-04-15 09:34:15.01604', 'root', '2020-04-23 15:52:20.883', 60);
INSERT INTO "public"."base_exam" VALUES ('4803cc7db5234941b0090ea8cfc37a97', '测试考试0426-1', '972c142373294d06ae64eb6982ecfc32', '2020-04-25 00:00:00', '2020-04-29 00:00:00', NULL, 'root', '2020-04-26 16:08:36.746814', NULL, NULL, 10);
INSERT INTO "public"."base_exam" VALUES ('819ea7f0dec648e19baaff1d932dfe5f', '测试考试0426-2', '85378292de6e4fc8a7a8cc967e0ae442', '2020-04-25 00:00:00', '2020-04-30 00:00:00', NULL, 'root', '2020-04-26 16:52:28.134915', NULL, NULL, 20);

-- ----------------------------
-- Table structure for base_journalism
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_journalism";
CREATE TABLE "public"."base_journalism" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(200) COLLATE "pg_catalog"."default",
  "introduction" text COLLATE "pg_catalog"."default",
  "create_user" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(32) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."base_journalism"."id" IS '新闻ID';
COMMENT ON COLUMN "public"."base_journalism"."name" IS '新闻名称';
COMMENT ON COLUMN "public"."base_journalism"."introduction" IS '新闻简介';
COMMENT ON COLUMN "public"."base_journalism"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_journalism"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_journalism"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."base_journalism"."update_time" IS '修改时间';
COMMENT ON TABLE "public"."base_journalism" IS '新闻表';

-- ----------------------------
-- Table structure for base_notice
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_notice";
CREATE TABLE "public"."base_notice" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(200) COLLATE "pg_catalog"."default",
  "introduction" text COLLATE "pg_catalog"."default",
  "create_user" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(32) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."base_notice"."id" IS '通知公告ID';
COMMENT ON COLUMN "public"."base_notice"."name" IS '通知公告名称';
COMMENT ON COLUMN "public"."base_notice"."introduction" IS '通知公告简介';
COMMENT ON COLUMN "public"."base_notice"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_notice"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_notice"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."base_notice"."update_time" IS '修改时间';
COMMENT ON TABLE "public"."base_notice" IS '通知公告表';

-- ----------------------------
-- Table structure for base_statistics
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_statistics";
CREATE TABLE "public"."base_statistics" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "document_id" varchar(32) COLLATE "pg_catalog"."default",
  "category_id" varchar(32) COLLATE "pg_catalog"."default",
  "type" int4,
  "create_time" date
)
;

-- ----------------------------
-- Records of base_statistics
-- ----------------------------
INSERT INTO "public"."base_statistics" VALUES ('2', 'b298d6aca3fe4205b1b65319b2dfe835', 'e34b8eb18de243808385baab6419c21f', 1, '2020-04-29');
INSERT INTO "public"."base_statistics" VALUES ('10', '234234', '123123', 1, '2020-04-24');
INSERT INTO "public"."base_statistics" VALUES ('11', '123sad', '123123', 1, '2020-04-24');
INSERT INTO "public"."base_statistics" VALUES ('12', 'asd12', '123123', 1, '2020-04-25');
INSERT INTO "public"."base_statistics" VALUES ('8', '23123', 'e34b8eb18de243808385baab6419c21f', 1, '2020-04-24');
INSERT INTO "public"."base_statistics" VALUES ('7', '12313', '123123', 1, '2020-04-25');
INSERT INTO "public"."base_statistics" VALUES ('5', '98dbca7ed1e14ac08e75fee0c227f9cc', '123123', 1, '2020-04-26');
INSERT INTO "public"."base_statistics" VALUES ('3', 'a433b57d6734421093cc262ecfe0afe7', 'e34b8eb18de243808385baab6419c21f', 1, '2020-04-29');
INSERT INTO "public"."base_statistics" VALUES ('9', '23123', '123123', 1, '2020-04-23');
INSERT INTO "public"."base_statistics" VALUES ('13', 'asd1', '123123', 0, '2020-05-06');
INSERT INTO "public"."base_statistics" VALUES ('14', 'asd123', 'e34b8eb18de243808385baab6419c21f', 0, '2020-05-06');
INSERT INTO "public"."base_statistics" VALUES ('15', 'a23ad', '123123', 0, '2020-05-02');
INSERT INTO "public"."base_statistics" VALUES ('17', 'asd12313', '22asd', 0, '2020-05-07');
INSERT INTO "public"."base_statistics" VALUES ('16', '123123', '22asd', 0, '2020-05-05');
INSERT INTO "public"."base_statistics" VALUES ('1', '7d451763b7e24ba38a29b0e16a66f4f4', 'e34b8eb18de243808385baab6419c21f', 1, '2020-05-06');
INSERT INTO "public"."base_statistics" VALUES ('6', 'a53c94cbf7094d659fdcafce29f78330', '123123', 1, '2020-05-06');
INSERT INTO "public"."base_statistics" VALUES ('4', 'a326f5f1a2f54432b9ceff04fc6de38e', '123123', 1, '2020-05-05');
INSERT INTO "public"."base_statistics" VALUES ('18', '1231', '123123', 1, '2020-05-07');
INSERT INTO "public"."base_statistics" VALUES ('19', '萨尔23', '123123', 1, '2020-05-04');
INSERT INTO "public"."base_statistics" VALUES ('20', '鹅23', '123123', 1, '2020-05-03');
INSERT INTO "public"."base_statistics" VALUES ('32', '阿萨德23', '123123', 1, '2020-05-02');
INSERT INTO "public"."base_statistics" VALUES ('21', '艾尔231', '123123', 1, '2020-05-01');
INSERT INTO "public"."base_statistics" VALUES ('22', '123123123', '123123', 1, '2020-04-30');
INSERT INTO "public"."base_statistics" VALUES ('23', '顺德区翁', '123123', 1, '2020-05-05');

-- ----------------------------
-- Table structure for base_subject
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_subject";
CREATE TABLE "public"."base_subject" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(150) COLLATE "pg_catalog"."default",
  "type" int2 DEFAULT 0,
  "difficulty" varchar(30) COLLATE "pg_catalog"."default",
  "score" numeric(3,1) DEFAULT 0,
  "analysis" varchar(300) COLLATE "pg_catalog"."default",
  "options" jsonb,
  "answers" jsonb,
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."base_subject"."id" IS '题目id';
COMMENT ON COLUMN "public"."base_subject"."name" IS '题目名称';
COMMENT ON COLUMN "public"."base_subject"."type" IS '题目类型（0：单选；1：多选；2：选择）';
COMMENT ON COLUMN "public"."base_subject"."difficulty" IS '题目难度（字典表编码）';
COMMENT ON COLUMN "public"."base_subject"."score" IS '题目分值';
COMMENT ON COLUMN "public"."base_subject"."analysis" IS '题目解析';
COMMENT ON COLUMN "public"."base_subject"."options" IS '题选项（json）';
COMMENT ON COLUMN "public"."base_subject"."answers" IS '答案（json）';
COMMENT ON COLUMN "public"."base_subject"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_subject"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_subject"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."base_subject"."update_time" IS '修改时间';
COMMENT ON TABLE "public"."base_subject" IS '题目表';

-- ----------------------------
-- Records of base_subject
-- ----------------------------
INSERT INTO "public"."base_subject" VALUES ('162d8b4596174f59b1ce46c23b0e81f1', '测试导入0409-1', 0, 'easy', 5.0, '测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1', '[{"key": "A", "value": "测试导入0409-1选项A"}, {"key": "B", "value": "测试导入0409-1选项B"}, {"key": "C", "value": "测试导入0409-1选项C"}, {"key": "D", "value": "测试导入0409-1选项D"}]', '["A"]', 'root', '2020-05-08 11:09:42', NULL, NULL);
INSERT INTO "public"."base_subject" VALUES ('fd924ee74e4b40509f9c4a7f63f36575', '测试导入0409-3', 0, 'difficulty', 3.0, '测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3', '[{"key": "A", "value": "测试导入0409-3选项A"}, {"key": "B", "value": "测试导入0409-3选项B"}, {"key": "C", "value": "测试导入0409-3选项C"}, {"key": "D", "value": "测试导入0409-3选项D"}]', '["B"]', 'root', '2020-05-07 11:09:42', NULL, NULL);
INSERT INTO "public"."base_subject" VALUES ('d75267dfccc94cc991fa40e44e5ac22d', '小小小1111', 1, 'easy', 5.0, '哒哒哒哒哒哒多多ddddddddddddddd', '[{"key": "A", "value": "顶顶顶顶ss"}, {"key": "B", "value": "少时诵诗书ss"}, {"key": "C", "value": "三生三世dd"}]', '["B", "A", "C"]', 'root', '2020-05-05 17:53:43', 'root', '2020-04-08 18:08:22.385');
INSERT INTO "public"."base_subject" VALUES ('37375c551d444de78686b79f21b83c0e', '测试导入0409-2', 1, 'commonly', 2.0, '测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2', '[{"key": "A", "value": "测试导入0409-2选项A"}, {"key": "B", "value": "测试导入0409-2选项B"}, {"key": "C", "value": "测试导入0409-2选项C"}, {"key": "D", "value": "测试导入0409-2选项D"}]', '["A", "B", "C"]', 'root', '2020-05-02 11:09:42', NULL, NULL);

-- ----------------------------
-- Table structure for base_test_paper
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_test_paper";
CREATE TABLE "public"."base_test_paper" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "subjects" jsonb,
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "name" varchar(150) COLLATE "pg_catalog"."default" NOT NULL,
  "total_num" int4,
  "total_score" numeric(3,1)
)
;
COMMENT ON COLUMN "public"."base_test_paper"."id" IS '试卷id';
COMMENT ON COLUMN "public"."base_test_paper"."subjects" IS '试卷题目（json）';
COMMENT ON COLUMN "public"."base_test_paper"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_test_paper"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_test_paper"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."base_test_paper"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."base_test_paper"."name" IS '试卷名称';
COMMENT ON COLUMN "public"."base_test_paper"."total_num" IS '题目总数量';
COMMENT ON COLUMN "public"."base_test_paper"."total_score" IS '题目总分';
COMMENT ON TABLE "public"."base_test_paper" IS '试卷表';

-- ----------------------------
-- Records of base_test_paper
-- ----------------------------
INSERT INTO "public"."base_test_paper" VALUES ('fae080e25230452faf24b289146b6c88', '[{"id": "37375c551d444de78686b79f21b83c0e", "name": "测试导入0409-2", "type": 1, "score": 2, "search": null, "answers": "[\"A\", \"B\", \"C\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-2选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-2选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-2选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-2选项D\"}]", "orderBy": null, "analysis": "测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "commonly", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "一般", "beginCreateTime": null}, {"id": "fd924ee74e4b40509f9c4a7f63f36575", "name": "测试导入0409-3", "type": 0, "score": 3, "search": null, "answers": "[\"B\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-3选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-3选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-3选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-3选项D\"}]", "orderBy": null, "analysis": "测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "difficulty", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "困难", "beginCreateTime": null}]', 'root', '2020-04-13 10:45:47.092619', NULL, NULL, '测试试卷0413-1', 2, 5.0);
INSERT INTO "public"."base_test_paper" VALUES ('991970c05c584f379149bab3e6742d3c', '[{"id": "162d8b4596174f59b1ce46c23b0e81f1", "name": "测试导入0409-1", "type": 0, "index": 0, "score": 5, "search": null, "answers": "[\"A\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-1选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-1选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-1选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-1选项D\"}]", "orderBy": null, "analysis": "测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "easy", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "简单", "beginCreateTime": null}, {"id": "37375c551d444de78686b79f21b83c0e", "name": "测试导入0409-2", "type": 1, "index": 1, "score": 2, "search": null, "answers": "[\"A\", \"B\", \"C\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-2选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-2选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-2选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-2选项D\"}]", "orderBy": null, "analysis": "测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "commonly", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "一般", "beginCreateTime": null}, {"id": "fd924ee74e4b40509f9c4a7f63f36575", "name": "测试导入0409-3", "type": 0, "index": 2, "score": 3, "search": null, "answers": "[\"B\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-3选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-3选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-3选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-3选项D\"}]", "orderBy": null, "analysis": "测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "difficulty", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "困难", "beginCreateTime": null}]', 'root', '2020-04-13 10:48:29.020498', 'root', '2020-04-13 17:18:07.893', '测试试卷0413-2', 3, 10.0);
INSERT INTO "public"."base_test_paper" VALUES ('972c142373294d06ae64eb6982ecfc32', '[{"id": "162d8b4596174f59b1ce46c23b0e81f1", "name": "测试导入0409-1", "type": 0, "index": 0, "score": 5, "search": null, "answers": "[\"A\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-1选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-1选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-1选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-1选项D\"}]", "orderBy": null, "analysis": "测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "easy", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "简单", "beginCreateTime": null}, {"id": "37375c551d444de78686b79f21b83c0e", "name": "测试导入0409-2", "type": 1, "index": 1, "score": 2, "search": null, "answers": "[\"A\", \"B\", \"C\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-2选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-2选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-2选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-2选项D\"}]", "orderBy": null, "analysis": "测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "commonly", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "一般", "beginCreateTime": null}, {"id": "fd924ee74e4b40509f9c4a7f63f36575", "name": "测试导入0409-3", "type": 0, "index": 2, "score": 3, "search": null, "answers": "[\"B\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-3选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-3选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-3选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-3选项D\"}]", "orderBy": null, "analysis": "测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3测试导入0409-3", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "difficulty", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "困难", "beginCreateTime": null}, {"id": "d75267dfccc94cc991fa40e44e5ac22d", "name": "小小小1111", "type": 1, "index": 3, "score": 5, "search": null, "answers": "[\"B\", \"A\", \"C\"]", "options": "[{\"key\": \"A\", \"value\": \"顶顶顶顶ss\"}, {\"key\": \"B\", \"value\": \"少时诵诗书ss\"}, {\"key\": \"C\", \"value\": \"三生三世dd\"}]", "orderBy": null, "analysis": "哒哒哒哒哒哒多多ddddddddddddddd", "disabled": true, "createTime": 1586339623952, "createUser": null, "difficulty": "easy", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "简单", "beginCreateTime": null}]', 'root', '2020-04-13 16:18:25.494978', 'root', '2020-04-13 17:17:44.946', '测试试卷0413-3排序', 4, 15.0);
INSERT INTO "public"."base_test_paper" VALUES ('85378292de6e4fc8a7a8cc967e0ae442', '[{"id": "d75267dfccc94cc991fa40e44e5ac22d", "name": "小小小1111", "type": 1, "index": 0, "score": 5, "search": null, "answers": "[\"B\", \"A\", \"C\"]", "options": "[{\"key\": \"A\", \"value\": \"顶顶顶顶ss\"}, {\"key\": \"B\", \"value\": \"少时诵诗书ss\"}, {\"key\": \"C\", \"value\": \"三生三世dd\"}]", "orderBy": null, "analysis": "哒哒哒哒哒哒多多ddddddddddddddd", "disabled": true, "createTime": 1586339623952, "createUser": null, "difficulty": "easy", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "简单", "beginCreateTime": null}, {"id": "162d8b4596174f59b1ce46c23b0e81f1", "name": "测试导入0409-1", "type": 0, "index": 1, "score": 5, "search": null, "answers": "[\"A\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-1选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-1选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-1选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-1选项D\"}]", "orderBy": null, "analysis": "测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1测试导入0409-1", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "easy", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "简单", "beginCreateTime": null}, {"id": "37375c551d444de78686b79f21b83c0e", "name": "测试导入0409-2", "type": 1, "index": 2, "score": 2, "search": null, "answers": "[\"A\", \"B\", \"C\"]", "options": "[{\"key\": \"A\", \"value\": \"测试导入0409-2选项A\"}, {\"key\": \"B\", \"value\": \"测试导入0409-2选项B\"}, {\"key\": \"C\", \"value\": \"测试导入0409-2选项C\"}, {\"key\": \"D\", \"value\": \"测试导入0409-2选项D\"}]", "orderBy": null, "analysis": "测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2测试导入0409-2", "disabled": true, "createTime": 1586401782975, "createUser": null, "difficulty": "commonly", "updateTime": null, "updateUser": null, "endCreateTime": null, "difficultyName": "一般", "beginCreateTime": null}]', 'root', '2020-04-13 17:39:21.528575', 'root', '2020-04-13 17:39:41.998', '测试试卷0413-4', 3, 12.0);

-- ----------------------------
-- Table structure for base_topic
-- ----------------------------
DROP TABLE IF EXISTS "public"."base_topic";
CREATE TABLE "public"."base_topic" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(150) COLLATE "pg_catalog"."default",
  "circle_id" varchar(32) COLLATE "pg_catalog"."default",
  "theme" varchar(150) COLLATE "pg_catalog"."default",
  "content" text COLLATE "pg_catalog"."default",
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."base_topic"."id" IS '话题ID';
COMMENT ON COLUMN "public"."base_topic"."name" IS '话题名称';
COMMENT ON COLUMN "public"."base_topic"."circle_id" IS '圈子ID';
COMMENT ON COLUMN "public"."base_topic"."theme" IS '主题';
COMMENT ON COLUMN "public"."base_topic"."content" IS '话题类容';
COMMENT ON COLUMN "public"."base_topic"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."base_topic"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."base_topic"."update_user" IS '修改人';
COMMENT ON TABLE "public"."base_topic" IS '话题表';

-- ----------------------------
-- Records of base_topic
-- ----------------------------
INSERT INTO "public"."base_topic" VALUES ('282849ba403b40b6b93c07578aebc0df', ' 测试圈1--测试话题1', '9add2f57b7a64163a0a063cfc9f6fcb7', NULL, '<p>&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1&nbsp;测试圈1--测试话题1</p>', 'root', '2020-04-03 14:50:46.496849', NULL);
INSERT INTO "public"."base_topic" VALUES ('6d8a647575d14f07979fec20f3fe30b7', '测试圈1--测试话题2', '9add2f57b7a64163a0a063cfc9f6fcb7', NULL, '<p>测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2测试圈1--测试话题2</p>', 'root', '2020-04-03 14:54:45.073899', NULL);
INSERT INTO "public"."base_topic" VALUES ('9286ad6646ad444eb7a274d231b90d7f', '测试圈2--测试话题1', '7c19976e01b5483a8c257c0fb3718b89', NULL, '<p>测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1测试圈2--测试话题1</p>', 'root', '2020-04-03 15:00:36.805395', NULL);

-- ----------------------------
-- Table structure for bus_collection
-- ----------------------------
DROP TABLE IF EXISTS "public"."bus_collection";
CREATE TABLE "public"."bus_collection" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "type" int2,
  "document_id" varchar(32) COLLATE "pg_catalog"."default",
  "topic_id" varchar(32) COLLATE "pg_catalog"."default",
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."bus_collection"."id" IS '点赞id';
COMMENT ON COLUMN "public"."bus_collection"."type" IS '收藏类型（0：文档类型；1：话题类型）';
COMMENT ON COLUMN "public"."bus_collection"."document_id" IS '文档ID';
COMMENT ON COLUMN "public"."bus_collection"."topic_id" IS '话题ID';
COMMENT ON COLUMN "public"."bus_collection"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."bus_collection"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."bus_collection"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."bus_collection"."update_time" IS '修改时间';
COMMENT ON TABLE "public"."bus_collection" IS '点赞表';

-- ----------------------------
-- Records of bus_collection
-- ----------------------------
INSERT INTO "public"."bus_collection" VALUES ('eb452c5c1e944ad396326a0155b81bed', 0, 'b298d6aca3fe4205b1b65319b2dfe835', NULL, 'root', '2020-05-06 15:04:10.509123', NULL, NULL);

-- ----------------------------
-- Table structure for bus_comment
-- ----------------------------
DROP TABLE IF EXISTS "public"."bus_comment";
CREATE TABLE "public"."bus_comment" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "pid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT 0,
  "type" int2,
  "document_id" varchar(32) COLLATE "pg_catalog"."default",
  "topic_id" varchar(32) COLLATE "pg_catalog"."default",
  "reply_user" varchar(150) COLLATE "pg_catalog"."default",
  "content" text COLLATE "pg_catalog"."default",
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "score" numeric DEFAULT 0.0
)
;
COMMENT ON COLUMN "public"."bus_comment"."id" IS '评论ID';
COMMENT ON COLUMN "public"."bus_comment"."pid" IS '评论pid（回复）';
COMMENT ON COLUMN "public"."bus_comment"."type" IS '评论类型（0：文档类型；1：话题类型）';
COMMENT ON COLUMN "public"."bus_comment"."document_id" IS '文档ID';
COMMENT ON COLUMN "public"."bus_comment"."topic_id" IS '话题ID';
COMMENT ON COLUMN "public"."bus_comment"."reply_user" IS '回复用户';
COMMENT ON COLUMN "public"."bus_comment"."content" IS '评论类容';
COMMENT ON COLUMN "public"."bus_comment"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."bus_comment"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."bus_comment"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."bus_comment"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."bus_comment"."score" IS '评分';
COMMENT ON TABLE "public"."bus_comment" IS '评论表';

-- ----------------------------
-- Records of bus_comment
-- ----------------------------
INSERT INTO "public"."bus_comment" VALUES ('41a4199ad83540de9e4aa428eb700d6e', '0', 0, 'b1dc67d9ddc6403b8eb52b920407ff95', NULL, NULL, '测试1', 'root', '2020-03-31 11:20:59.790266', NULL, NULL, 4.5);
INSERT INTO "public"."bus_comment" VALUES ('9e65cda021e548979f6bb2ad79cf9143', '41a4199ad83540de9e4aa428eb700d6e', 0, 'b1dc67d9ddc6403b8eb52b920407ff95', NULL, 'root', 'Nihao1', 'root', '2020-03-31 11:26:54.660421', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('c21588e84505441b9aa0552e68c6232d', '0', 0, 'b1dc67d9ddc6403b8eb52b920407ff95', NULL, NULL, 'ceshi2', 'root', '2020-03-31 11:29:26.341931', NULL, NULL, 5);
INSERT INTO "public"."bus_comment" VALUES ('e256ee82c76e48c7a4885d00b5d915e3', '9e65cda021e548979f6bb2ad79cf9143', 0, 'b1dc67d9ddc6403b8eb52b920407ff95', NULL, 'root', 'nihao', 'root', '2020-03-31 11:31:42.755189', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('cfb0ef798cea4b9bbcc512fa086fb84e', '0', 0, 'b1dc67d9ddc6403b8eb52b920407ff95', NULL, NULL, 'dfdssfdsa', 'root', '2020-03-31 11:31:57.82746', NULL, NULL, 3.5);
INSERT INTO "public"."bus_comment" VALUES ('9dd7042be57f4a21a6bf684757a85d82', 'cfb0ef798cea4b9bbcc512fa086fb84e', 0, 'b1dc67d9ddc6403b8eb52b920407ff95', NULL, '', '回复dfdssfdsa--1', 'root', '2020-03-31 13:44:49.120649', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('a7efa6132f3640ca8e421031cbc5a05f', '9dd7042be57f4a21a6bf684757a85d82', 0, 'b1dc67d9ddc6403b8eb52b920407ff95', NULL, 'root', '回复dfdssfdsa--2', 'root', '2020-03-31 13:45:05.378722', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('cab563533341437b8a06d4672ade15f1', '41a4199ad83540de9e4aa428eb700d6e', 0, 'b1dc67d9ddc6403b8eb52b920407ff95', NULL, '', '回复测试1---1', 'root', '2020-03-31 13:45:48.769264', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('55a7cafc0e02480d94775812e1f17f03', '0', 0, 'b1dc67d9ddc6403b8eb52b920407ff95', NULL, NULL, '测试44444', 'root', '2020-03-31 13:46:36.739626', NULL, NULL, 1);
INSERT INTO "public"."bus_comment" VALUES ('f4bea0adf47242f0828e35120037a5f7', '0', 0, 'b298d6aca3fe4205b1b65319b2dfe835', NULL, NULL, '测试', 'root', '2020-04-02 12:15:07.449601', NULL, NULL, 3.5);
INSERT INTO "public"."bus_comment" VALUES ('365de91543f84341b4a30264e9a75ab3', 'f4bea0adf47242f0828e35120037a5f7', 0, 'b298d6aca3fe4205b1b65319b2dfe835', NULL, '', 'nihao', 'root', '2020-04-03 17:10:03.80178', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('b547efc466c1494eabdbe2fc466cf2df', '0', 0, 'b298d6aca3fe4205b1b65319b2dfe835', NULL, NULL, 'ceshi2', 'root', '2020-04-03 17:10:12.453076', NULL, NULL, 4.5);
INSERT INTO "public"."bus_comment" VALUES ('aaed3b9be6e44df599df12f88bf000d6', '0', 1, NULL, '282849ba403b40b6b93c07578aebc0df', NULL, '话题评论测试1', 'root', '2020-04-03 17:17:49.43718', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('3362ae18586a46288db27bd300bd0bae', 'aaed3b9be6e44df599df12f88bf000d6', 1, NULL, '282849ba403b40b6b93c07578aebc0df', '', '你好', 'root', '2020-04-03 17:21:53.361058', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('046ff38a5802477d8d150fafd9d240ed', 'aaed3b9be6e44df599df12f88bf000d6', 1, NULL, '282849ba403b40b6b93c07578aebc0df', '', 'nihao ', 'root', '2020-04-03 17:22:44.648299', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('b25f6e56915244cb8540663550619af4', '0', 1, NULL, '282849ba403b40b6b93c07578aebc0df', NULL, '话题评论测试2', 'root', '2020-04-03 17:23:01.935476', NULL, NULL, NULL);
INSERT INTO "public"."bus_comment" VALUES ('12a1a9e8469546b08dbe5df630a630ee', '0', 1, NULL, '9286ad6646ad444eb7a274d231b90d7f', NULL, '测试1', 'root', '2020-04-08 11:12:19.256739', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for bus_document_record
-- ----------------------------
DROP TABLE IF EXISTS "public"."bus_document_record";
CREATE TABLE "public"."bus_document_record" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "document_id" varchar(32) COLLATE "pg_catalog"."default",
  "dept_id" varchar(32) COLLATE "pg_catalog"."default",
  "type" int2,
  "status" int2 DEFAULT 0,
  "create_user" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "reserve1" varchar(255) COLLATE "pg_catalog"."default",
  "reserve2" varchar COLLATE "pg_catalog"."default",
  "reserve3" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."bus_document_record"."id" IS '文档记录ID';
COMMENT ON COLUMN "public"."bus_document_record"."document_id" IS '文档ID';
COMMENT ON COLUMN "public"."bus_document_record"."dept_id" IS '部门ID';
COMMENT ON COLUMN "public"."bus_document_record"."type" IS '记录类型（0：编辑；1：下载；2：纠错）';
COMMENT ON COLUMN "public"."bus_document_record"."status" IS '状态（0：未确认；1：已确认）';
COMMENT ON COLUMN "public"."bus_document_record"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."bus_document_record"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."bus_document_record"."reserve1" IS '预留字段1';
COMMENT ON COLUMN "public"."bus_document_record"."reserve2" IS '预留字段2';
COMMENT ON COLUMN "public"."bus_document_record"."reserve3" IS '预留字段2';
COMMENT ON TABLE "public"."bus_document_record" IS '文档记录表';

-- ----------------------------
-- Records of bus_document_record
-- ----------------------------
INSERT INTO "public"."bus_document_record" VALUES ('3b2b15be3c414b098aa15a73fac234b4', 'b298d6aca3fe4205b1b65319b2dfe835', '1', 2, 0, 'root', '2020-04-01 10:15:41.272', NULL, '<p>dfdfd</p>', NULL);
INSERT INTO "public"."bus_document_record" VALUES ('c5f1279d1c094327b40a6806ad5af5b7', 'b298d6aca3fe4205b1b65319b2dfe835', '1', 1, 0, 'root', '2020-04-01 14:55:45.522', NULL, NULL, NULL);
INSERT INTO "public"."bus_document_record" VALUES ('7aaecb91e4d649299348046d52d17ba7', 'b298d6aca3fe4205b1b65319b2dfe835', '1', 0, 0, 'root', '2020-04-01 15:49:40.898', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for bus_project
-- ----------------------------
DROP TABLE IF EXISTS "public"."bus_project";
CREATE TABLE "public"."bus_project" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(90) COLLATE "pg_catalog"."default",
  "type" int4 DEFAULT 0,
  "total_money" int8,
  "begin_date" date,
  "end_date" date,
  "term" int4,
  "status" int4 DEFAULT 0,
  "introduction" varchar(1000) COLLATE "pg_catalog"."default",
  "customer" varchar(90) COLLATE "pg_catalog"."default",
  "conscientious_user" varchar(32) COLLATE "pg_catalog"."default",
  "phone" varchar(11) COLLATE "pg_catalog"."default",
  "landline" varchar(15) COLLATE "pg_catalog"."default",
  "customer_addr" varchar(150) COLLATE "pg_catalog"."default",
  "enclosures" jsonb,
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "stage" int4,
  "audit_time" timestamp(6),
  "revoke_time" timestamp(6),
  "proc_id" varchar(32) COLLATE "pg_catalog"."default",
  "risk_description" varchar(500) COLLATE "pg_catalog"."default",
  "examine_status" int4,
  "examine_msg" varchar(255) COLLATE "pg_catalog"."default",
  "charge_person" varchar(32) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."bus_project"."id" IS '项目id';
COMMENT ON COLUMN "public"."bus_project"."name" IS '项目名称';
COMMENT ON COLUMN "public"."bus_project"."type" IS '项目类型（0：内部项目；1：外部项目）';
COMMENT ON COLUMN "public"."bus_project"."total_money" IS '项目总金额';
COMMENT ON COLUMN "public"."bus_project"."begin_date" IS '项目开始时间';
COMMENT ON COLUMN "public"."bus_project"."end_date" IS '项目结束时间';
COMMENT ON COLUMN "public"."bus_project"."term" IS '项目期限（天）';
COMMENT ON COLUMN "public"."bus_project"."status" IS '项目状态（-1：回收站；0：等待开始；1：进行中；2：已完工）';
COMMENT ON COLUMN "public"."bus_project"."introduction" IS '项目简介';
COMMENT ON COLUMN "public"."bus_project"."customer" IS '客服名称';
COMMENT ON COLUMN "public"."bus_project"."conscientious_user" IS '负责人';
COMMENT ON COLUMN "public"."bus_project"."phone" IS '负责人手机号';
COMMENT ON COLUMN "public"."bus_project"."landline" IS '负责人座机';
COMMENT ON COLUMN "public"."bus_project"."customer_addr" IS '客户地址';
COMMENT ON COLUMN "public"."bus_project"."enclosures" IS '项目附件（多个文件分号隔开）';
COMMENT ON COLUMN "public"."bus_project"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."bus_project"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."bus_project"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."bus_project"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."bus_project"."stage" IS '项目阶段';
COMMENT ON COLUMN "public"."bus_project"."audit_time" IS '审核通过时间';
COMMENT ON COLUMN "public"."bus_project"."revoke_time" IS '撤销时间';
COMMENT ON COLUMN "public"."bus_project"."proc_id" IS '流程id';
COMMENT ON COLUMN "public"."bus_project"."risk_description" IS '风险描述';
COMMENT ON COLUMN "public"."bus_project"."examine_status" IS '审核状态 0: 通过  1：不通过   2：未审核';
COMMENT ON COLUMN "public"."bus_project"."examine_msg" IS '审核信息';
COMMENT ON COLUMN "public"."bus_project"."charge_person" IS '项目负责人';
COMMENT ON TABLE "public"."bus_project" IS '项目表';

-- ----------------------------
-- Records of bus_project
-- ----------------------------
INSERT INTO "public"."bus_project" VALUES ('3de6fefe32e742d4878b9d0d4c837612', '11', NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', '', '', '[{"url": "/file/2020-04-26/20200426024125-2213123啊.txt", "addr": "C:\\Users\\Administrator\\Desktop\\知识库\\knowledgebase\\knowledgebase\\upload\\2020-04-26\\20200426024125-2213123啊.txt", "name": "2213123啊.txt"}]', 'root', '2020-04-26 14:38:38.261729', 'root', '2020-04-27 14:51:22.934', NULL, NULL, NULL, NULL, 'aasd123', NULL, NULL, '');

-- ----------------------------
-- Table structure for bus_project_stage
-- ----------------------------
DROP TABLE IF EXISTS "public"."bus_project_stage";
CREATE TABLE "public"."bus_project_stage" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(150) COLLATE "pg_catalog"."default",
  "blame_user_id" varchar(32) COLLATE "pg_catalog"."default",
  "progress" varchar(150) COLLATE "pg_catalog"."default",
  "valid_day" int4,
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(0),
  "project_id" varchar(32) COLLATE "pg_catalog"."default",
  "begin_date" date,
  "end_date" date
)
;
COMMENT ON COLUMN "public"."bus_project_stage"."id" IS '项目阶段ID';
COMMENT ON COLUMN "public"."bus_project_stage"."name" IS '阶段名称';
COMMENT ON COLUMN "public"."bus_project_stage"."blame_user_id" IS '责任人ID';
COMMENT ON COLUMN "public"."bus_project_stage"."progress" IS '进度';
COMMENT ON COLUMN "public"."bus_project_stage"."valid_day" IS '有效天数';
COMMENT ON COLUMN "public"."bus_project_stage"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."bus_project_stage"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."bus_project_stage"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."bus_project_stage"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."bus_project_stage"."project_id" IS '项目ID';
COMMENT ON COLUMN "public"."bus_project_stage"."begin_date" IS '开始时间';
COMMENT ON COLUMN "public"."bus_project_stage"."end_date" IS '结束时间';
COMMENT ON TABLE "public"."bus_project_stage" IS '项目阶段表';

-- ----------------------------
-- Records of bus_project_stage
-- ----------------------------
INSERT INTO "public"."bus_project_stage" VALUES ('742de437d6d54acb9b0dafcc240fface', '1234', '1', '1', 13, 'root', '2020-04-26 16:17:35', NULL, NULL, '3de6fefe32e742d4878b9d0d4c837612', '2020-04-17', '2020-04-24');
INSERT INTO "public"."bus_project_stage" VALUES ('8014dda3832445f2a6ec90085948aacd', '11啊', '44ca0fe1a562407eb76ae084fd129fc1', '0', 1, 'root', '2020-04-27 09:57:53', 'root', '2020-04-27 14:42:30', '3de6fefe32e742d4878b9d0d4c837612', '2020-04-02', '2020-04-03');

-- ----------------------------
-- Table structure for bus_user_map_curriculum
-- ----------------------------
DROP TABLE IF EXISTS "public"."bus_user_map_curriculum";
CREATE TABLE "public"."bus_user_map_curriculum" (
  "user_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "curriculum_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "progress" numeric(5,2) DEFAULT 0,
  "status" int2 DEFAULT 0,
  "learn_time" timestamp(6),
  "learn_record" jsonb,
  "complete_status" int2 DEFAULT 0
)
;
COMMENT ON COLUMN "public"."bus_user_map_curriculum"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."bus_user_map_curriculum"."curriculum_id" IS '课程id';
COMMENT ON COLUMN "public"."bus_user_map_curriculum"."progress" IS '学习进度（%）';
COMMENT ON COLUMN "public"."bus_user_map_curriculum"."status" IS '学习状态（0：未学习；1：学习中；2：已结束）';
COMMENT ON COLUMN "public"."bus_user_map_curriculum"."learn_time" IS '学习时间（备用）';
COMMENT ON COLUMN "public"."bus_user_map_curriculum"."learn_record" IS '学习记录（课程资源json数组下标）';
COMMENT ON COLUMN "public"."bus_user_map_curriculum"."complete_status" IS '完成学习状态（0：未完成；1：已完成）';
COMMENT ON TABLE "public"."bus_user_map_curriculum" IS '用户课程关系表';

-- ----------------------------
-- Records of bus_user_map_curriculum
-- ----------------------------
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('5450ef6e0bb64c9aaec88527e77ffb49', '2', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('97f5d2015f3d4ab5827f9960d8a43ef9', '2', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('c6750204c06945fe8bc4a82d48e18d64', '95bb993dccff4e34a6a337f6c96376a1', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('9e2b3bc267144195936b2ed08b8863f1', '95bb993dccff4e34a6a337f6c96376a1', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('11845c14c31c44ea929de313ad194feb', '95bb993dccff4e34a6a337f6c96376a1', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', '95bb993dccff4e34a6a337f6c96376a1', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('cde659391d7944b19041be284ca7bccc', '95bb993dccff4e34a6a337f6c96376a1', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('5450ef6e0bb64c9aaec88527e77ffb49', '95bb993dccff4e34a6a337f6c96376a1', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('97f5d2015f3d4ab5827f9960d8a43ef9', '95bb993dccff4e34a6a337f6c96376a1', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('9e2b3bc267144195936b2ed08b8863f1', '1a0ca99c3f694467876429b57824c453', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', '1a0ca99c3f694467876429b57824c453', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('c6750204c06945fe8bc4a82d48e18d64', '956fa43206314502b69d5289514334a6', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('9e2b3bc267144195936b2ed08b8863f1', '956fa43206314502b69d5289514334a6', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('11845c14c31c44ea929de313ad194feb', '956fa43206314502b69d5289514334a6', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('8f705294e8c348bfa22602c1c48db433', '956fa43206314502b69d5289514334a6', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', '956fa43206314502b69d5289514334a6', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('cde659391d7944b19041be284ca7bccc', '956fa43206314502b69d5289514334a6', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('5450ef6e0bb64c9aaec88527e77ffb49', '956fa43206314502b69d5289514334a6', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('97f5d2015f3d4ab5827f9960d8a43ef9', '956fa43206314502b69d5289514334a6', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('469daad359b34676ade85832de90d517', '956fa43206314502b69d5289514334a6', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('1', '1a0ca99c3f694467876429b57824c453', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', '2', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('cde659391d7944b19041be284ca7bccc', '2', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('11845c14c31c44ea929de313ad194feb', '2', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('9e2b3bc267144195936b2ed08b8863f1', '2', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('c6750204c06945fe8bc4a82d48e18d64', '2', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('1', '956fa43206314502b69d5289514334a6', 100.00, 1, '2020-04-26 09:33:54.258', '[1, 0]', 1);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('9e2b3bc267144195936b2ed08b8863f1', '483d1e4161924082904e77d1a2388c02', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', '483d1e4161924082904e77d1a2388c02', 0.00, 0, NULL, NULL, 0);
INSERT INTO "public"."bus_user_map_curriculum" VALUES ('1', '483d1e4161924082904e77d1a2388c02', 100.00, 0, '2020-05-06 09:49:24.056', '[1, 0]', 1);

-- ----------------------------
-- Table structure for bus_user_map_exam
-- ----------------------------
DROP TABLE IF EXISTS "public"."bus_user_map_exam";
CREATE TABLE "public"."bus_user_map_exam" (
  "user_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "exam_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "submit_paper_time" varchar(32) COLLATE "pg_catalog"."default",
  "answers" jsonb,
  "score" numeric(3,1),
  "status" int2 DEFAULT 0,
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "submit_time" timestamp(6),
  "error_num" int4
)
;
COMMENT ON COLUMN "public"."bus_user_map_exam"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."bus_user_map_exam"."exam_id" IS '考试id';
COMMENT ON COLUMN "public"."bus_user_map_exam"."submit_paper_time" IS '交卷时间';
COMMENT ON COLUMN "public"."bus_user_map_exam"."answers" IS '交卷答案（json）';
COMMENT ON COLUMN "public"."bus_user_map_exam"."score" IS '分数';
COMMENT ON COLUMN "public"."bus_user_map_exam"."status" IS '考试状态（0：未考；1：已考）';
COMMENT ON COLUMN "public"."bus_user_map_exam"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."bus_user_map_exam"."submit_time" IS '提交时间';
COMMENT ON COLUMN "public"."bus_user_map_exam"."error_num" IS '错误数';
COMMENT ON TABLE "public"."bus_user_map_exam" IS '用户考试表';

-- ----------------------------
-- Records of bus_user_map_exam
-- ----------------------------
INSERT INTO "public"."bus_user_map_exam" VALUES ('1', '4960d8730cd94d4392182986ff5311f0', NULL, NULL, NULL, 0, '2020-04-15 09:32:59.25199', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('2', '4960d8730cd94d4392182986ff5311f0', NULL, NULL, NULL, 0, '2020-04-15 09:32:59.25199', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('8f705294e8c348bfa22602c1c48db433', '27461b0d490347b09c3d35d4d481f29c', NULL, NULL, NULL, 0, '2020-04-16 16:56:00.30112', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('1', '65d40be412e64cb5aa9d774de0888e76', NULL, NULL, NULL, 0, '2020-04-16 16:56:09.444896', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('8f705294e8c348bfa22602c1c48db433', '65d40be412e64cb5aa9d774de0888e76', NULL, NULL, NULL, 0, '2020-04-16 16:56:09.444896', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('469daad359b34676ade85832de90d517', '65d40be412e64cb5aa9d774de0888e76', NULL, NULL, NULL, 0, '2020-04-16 16:56:09.444896', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('1', '27461b0d490347b09c3d35d4d481f29c', '335174', '[["A", "B"], ["A"]]', 0.0, 1, '2020-04-16 16:56:00.30112', '2020-04-21 10:14:29.893', 2);
INSERT INTO "public"."bus_user_map_exam" VALUES ('c6750204c06945fe8bc4a82d48e18d64', 'f680ad5a6eaa4ca882c305e04decd8ac', NULL, NULL, NULL, 0, '2020-04-22 11:40:01.512134', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('9e2b3bc267144195936b2ed08b8863f1', 'f680ad5a6eaa4ca882c305e04decd8ac', NULL, NULL, NULL, 0, '2020-04-22 11:40:01.512134', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('11845c14c31c44ea929de313ad194feb', 'f680ad5a6eaa4ca882c305e04decd8ac', NULL, NULL, NULL, 0, '2020-04-22 11:40:01.512134', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('8f705294e8c348bfa22602c1c48db433', 'f680ad5a6eaa4ca882c305e04decd8ac', NULL, NULL, NULL, 0, '2020-04-22 11:40:01.512134', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', 'f680ad5a6eaa4ca882c305e04decd8ac', NULL, NULL, NULL, 0, '2020-04-22 11:40:01.512134', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('cde659391d7944b19041be284ca7bccc', 'f680ad5a6eaa4ca882c305e04decd8ac', NULL, NULL, NULL, 0, '2020-04-22 11:40:01.512134', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('5450ef6e0bb64c9aaec88527e77ffb49', 'f680ad5a6eaa4ca882c305e04decd8ac', NULL, NULL, NULL, 0, '2020-04-22 11:40:01.512134', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('97f5d2015f3d4ab5827f9960d8a43ef9', 'f680ad5a6eaa4ca882c305e04decd8ac', NULL, NULL, NULL, 0, '2020-04-22 11:40:01.512134', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('469daad359b34676ade85832de90d517', 'f680ad5a6eaa4ca882c305e04decd8ac', NULL, NULL, NULL, 0, '2020-04-22 11:40:01.512134', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('1', 'f680ad5a6eaa4ca882c305e04decd8ac', '19379', '[["B", "A"], ["B"]]', 3.0, 1, '2020-04-22 11:40:01.512134', '2020-04-22 12:12:42.157', 1);
INSERT INTO "public"."bus_user_map_exam" VALUES ('c6750204c06945fe8bc4a82d48e18d64', 'abae293d472f40338dac2450848317e3', NULL, NULL, NULL, 0, '2020-04-22 12:50:40.82013', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('9e2b3bc267144195936b2ed08b8863f1', 'abae293d472f40338dac2450848317e3', NULL, NULL, NULL, 0, '2020-04-22 12:50:40.82013', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('11845c14c31c44ea929de313ad194feb', 'abae293d472f40338dac2450848317e3', NULL, NULL, NULL, 0, '2020-04-22 12:50:40.82013', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('8f705294e8c348bfa22602c1c48db433', 'abae293d472f40338dac2450848317e3', NULL, NULL, NULL, 0, '2020-04-22 12:50:40.82013', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', 'abae293d472f40338dac2450848317e3', NULL, NULL, NULL, 0, '2020-04-22 12:50:40.82013', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('cde659391d7944b19041be284ca7bccc', 'abae293d472f40338dac2450848317e3', NULL, NULL, NULL, 0, '2020-04-22 12:50:40.82013', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('5450ef6e0bb64c9aaec88527e77ffb49', 'abae293d472f40338dac2450848317e3', NULL, NULL, NULL, 0, '2020-04-22 12:50:40.82013', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('97f5d2015f3d4ab5827f9960d8a43ef9', 'abae293d472f40338dac2450848317e3', NULL, NULL, NULL, 0, '2020-04-22 12:50:40.82013', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('469daad359b34676ade85832de90d517', 'abae293d472f40338dac2450848317e3', NULL, NULL, NULL, 0, '2020-04-22 12:50:40.82013', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('1', 'abae293d472f40338dac2450848317e3', '70709', '[["A"], ["A", "B"], ["C"], ["C", "B"]]', 5.0, 1, '2020-04-22 12:50:40.82013', '2020-04-22 12:52:08.227', 3);
INSERT INTO "public"."bus_user_map_exam" VALUES ('1', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('c6750204c06945fe8bc4a82d48e18d64', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('9e2b3bc267144195936b2ed08b8863f1', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('11845c14c31c44ea929de313ad194feb', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('8f705294e8c348bfa22602c1c48db433', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('cde659391d7944b19041be284ca7bccc', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('5450ef6e0bb64c9aaec88527e77ffb49', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('97f5d2015f3d4ab5827f9960d8a43ef9', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('469daad359b34676ade85832de90d517', '0e8301c4766b491d9fe85169194340a7', NULL, NULL, NULL, 0, '2020-04-23 15:52:20.885899', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('c6750204c06945fe8bc4a82d48e18d64', '4803cc7db5234941b0090ea8cfc37a97', NULL, NULL, NULL, 0, '2020-04-26 16:08:36.746814', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('9e2b3bc267144195936b2ed08b8863f1', '4803cc7db5234941b0090ea8cfc37a97', NULL, NULL, NULL, 0, '2020-04-26 16:08:36.746814', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('11845c14c31c44ea929de313ad194feb', '4803cc7db5234941b0090ea8cfc37a97', NULL, NULL, NULL, 0, '2020-04-26 16:08:36.746814', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('8f705294e8c348bfa22602c1c48db433', '4803cc7db5234941b0090ea8cfc37a97', NULL, NULL, NULL, 0, '2020-04-26 16:08:36.746814', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', '4803cc7db5234941b0090ea8cfc37a97', NULL, NULL, NULL, 0, '2020-04-26 16:08:36.746814', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('cde659391d7944b19041be284ca7bccc', '4803cc7db5234941b0090ea8cfc37a97', NULL, NULL, NULL, 0, '2020-04-26 16:08:36.746814', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('5450ef6e0bb64c9aaec88527e77ffb49', '4803cc7db5234941b0090ea8cfc37a97', NULL, NULL, NULL, 0, '2020-04-26 16:08:36.746814', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('469daad359b34676ade85832de90d517', '4803cc7db5234941b0090ea8cfc37a97', NULL, NULL, NULL, 0, '2020-04-26 16:08:36.746814', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('1', '4803cc7db5234941b0090ea8cfc37a97', '20871', '[["B"], ["B", "A"], ["B"], ["B", "A"]]', 3.0, 1, '2020-04-26 16:08:36.746814', '2020-04-26 16:47:09.909', 3);
INSERT INTO "public"."bus_user_map_exam" VALUES ('1', '819ea7f0dec648e19baaff1d932dfe5f', NULL, NULL, NULL, 0, '2020-04-26 16:52:28.134915', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('c6750204c06945fe8bc4a82d48e18d64', '819ea7f0dec648e19baaff1d932dfe5f', NULL, NULL, NULL, 0, '2020-04-26 16:52:28.134915', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('9e2b3bc267144195936b2ed08b8863f1', '819ea7f0dec648e19baaff1d932dfe5f', NULL, NULL, NULL, 0, '2020-04-26 16:52:28.134915', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('11845c14c31c44ea929de313ad194feb', '819ea7f0dec648e19baaff1d932dfe5f', NULL, NULL, NULL, 0, '2020-04-26 16:52:28.134915', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('8f705294e8c348bfa22602c1c48db433', '819ea7f0dec648e19baaff1d932dfe5f', NULL, NULL, NULL, 0, '2020-04-26 16:52:28.134915', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', '819ea7f0dec648e19baaff1d932dfe5f', NULL, NULL, NULL, 0, '2020-04-26 16:52:28.134915', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('cde659391d7944b19041be284ca7bccc', '819ea7f0dec648e19baaff1d932dfe5f', NULL, NULL, NULL, 0, '2020-04-26 16:52:28.134915', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('5450ef6e0bb64c9aaec88527e77ffb49', '819ea7f0dec648e19baaff1d932dfe5f', NULL, NULL, NULL, 0, '2020-04-26 16:52:28.134915', NULL, NULL);
INSERT INTO "public"."bus_user_map_exam" VALUES ('469daad359b34676ade85832de90d517', '819ea7f0dec648e19baaff1d932dfe5f', NULL, NULL, NULL, 0, '2020-04-26 16:52:28.134915', NULL, NULL);

-- ----------------------------
-- Table structure for bus_user_map_project
-- ----------------------------
DROP TABLE IF EXISTS "public"."bus_user_map_project";
CREATE TABLE "public"."bus_user_map_project" (
  "user_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "project_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "type" int2
)
;
COMMENT ON COLUMN "public"."bus_user_map_project"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."bus_user_map_project"."project_id" IS '项目id';
COMMENT ON COLUMN "public"."bus_user_map_project"."type" IS '关系类型（0：参与；1：推送）';
COMMENT ON TABLE "public"."bus_user_map_project" IS '用户项目关系表';

-- ----------------------------
-- Records of bus_user_map_project
-- ----------------------------
INSERT INTO "public"."bus_user_map_project" VALUES ('469daad359b34676ade85832de90d517', '728e17f737db4bb5bec0a6d6aeac9b42', 0);
INSERT INTO "public"."bus_user_map_project" VALUES ('8f705294e8c348bfa22602c1c48db433', '728e17f737db4bb5bec0a6d6aeac9b42', 1);
INSERT INTO "public"."bus_user_map_project" VALUES ('469daad359b34676ade85832de90d517', '728e17f737db4bb5bec0a6d6aeac9b42', 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_dept";
CREATE TABLE "public"."sys_dept" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "pid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT 0,
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."sys_dept"."id" IS '部门id';
COMMENT ON COLUMN "public"."sys_dept"."name" IS '部门名称';
COMMENT ON COLUMN "public"."sys_dept"."pid" IS '部门父id';
COMMENT ON COLUMN "public"."sys_dept"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."sys_dept"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_dept"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."sys_dept"."update_time" IS '修改时间';
COMMENT ON TABLE "public"."sys_dept" IS '部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO "public"."sys_dept" VALUES ('1', '和贯科技', '0', 'root', '2020-03-13 17:38:53.393892', NULL, NULL);
INSERT INTO "public"."sys_dept" VALUES ('3f5c14867adf44bd9b30e38c06eab067', '市场部', '1', 'root', '2020-03-13 17:39:40.263638', NULL, NULL);
INSERT INTO "public"."sys_dept" VALUES ('60b2b7b3b42d4f1485e27a9e8b715dac', '行政部', '1', 'root', '2020-03-13 17:39:14.680206', NULL, NULL);
INSERT INTO "public"."sys_dept" VALUES ('53ee053db77a40e38606fdd401900e16', '开发部', '1', 'root', '2020-03-24 11:20:25.330028', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_dict";
CREATE TABLE "public"."sys_dict" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(150) COLLATE "pg_catalog"."default",
  "coder" varchar(30) COLLATE "pg_catalog"."default",
  "group_coder" varchar(30) COLLATE "pg_catalog"."default",
  "weight" int2,
  "introduction" varchar(100) COLLATE "pg_catalog"."default",
  "reserve" varchar(300) COLLATE "pg_catalog"."default",
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" timestamptz(6),
  "udpate_time" timestamp(6),
  "type" int2 DEFAULT 0,
  "group_name" varchar(150) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_dict"."id" IS '字典id';
COMMENT ON COLUMN "public"."sys_dict"."name" IS '字典名称';
COMMENT ON COLUMN "public"."sys_dict"."coder" IS '字典编码';
COMMENT ON COLUMN "public"."sys_dict"."group_coder" IS '字典组编码';
COMMENT ON COLUMN "public"."sys_dict"."weight" IS '字典权重（用作排序，权重大在前）';
COMMENT ON COLUMN "public"."sys_dict"."introduction" IS '字典简介';
COMMENT ON COLUMN "public"."sys_dict"."reserve" IS '备用字段';
COMMENT ON COLUMN "public"."sys_dict"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."sys_dict"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_dict"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."sys_dict"."udpate_time" IS '修改时间';
COMMENT ON COLUMN "public"."sys_dict"."type" IS '字典类型（0：自定义；1：系统）';
COMMENT ON COLUMN "public"."sys_dict"."group_name" IS '字典组名称';
COMMENT ON TABLE "public"."sys_dict" IS '字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO "public"."sys_dict" VALUES ('1', '简单', 'easy', 'subject_type', NULL, '题型介绍', NULL, 'root', '2020-03-16 14:46:35.636', NULL, NULL, 1, '题目难度');
INSERT INTO "public"."sys_dict" VALUES ('2', '一般', 'commonly', 'subject_type', NULL, '题型介绍', NULL, 'root', '2020-03-16 14:47:57.274', NULL, NULL, 1, '题目难度');
INSERT INTO "public"."sys_dict" VALUES ('3', '困难', 'difficulty', 'subject_type', NULL, '题型介绍', NULL, 'root', '2020-03-16 14:48:35.978', NULL, NULL, 1, '题目难度');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_log";
CREATE TABLE "public"."sys_log" (
  "id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "coder" varchar(255) COLLATE "pg_catalog"."default",
  "operation_user" varchar(255) COLLATE "pg_catalog"."default",
  "operation_time" timestamp(6),
  "operation_msg" varchar(255) COLLATE "pg_catalog"."default",
  "operation_user_dept" varchar(255) COLLATE "pg_catalog"."default",
  "operation_user_ip" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_log"."id" IS '日志id';
COMMENT ON COLUMN "public"."sys_log"."coder" IS '日志编码';
COMMENT ON COLUMN "public"."sys_log"."operation_user" IS '操作人';
COMMENT ON COLUMN "public"."sys_log"."operation_time" IS '操作时间';
COMMENT ON COLUMN "public"."sys_log"."operation_msg" IS '操作信息';
COMMENT ON COLUMN "public"."sys_log"."operation_user_dept" IS '操作人所在部门';
COMMENT ON COLUMN "public"."sys_log"."operation_user_ip" IS '操作人ip地址';
COMMENT ON TABLE "public"."sys_log" IS '日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO "public"."sys_log" VALUES ('6563d891-1e73-4dca-b857-53b31bd9746a', '20200326055547', 'root', '2020-03-26 17:55:47.624', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('390e4278-f663-4990-a753-a48fbcb51e47', '20200326055320', 'anonymousUser', '2020-03-26 17:53:20.302', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('b3f1f0a0-dc37-4a03-a36b-8ac8806d16a1', '20200326055608', 'root', '2020-03-26 17:56:08.483', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('59f7e1cd-8a25-499e-a5c0-1d2d99342c9d', '20200326055838', 'root', '2020-03-26 17:58:38.658', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a46ca697-48b5-4cbc-acd9-5df4b449339d', '20200326060027', 'root', '2020-03-26 18:00:27.019', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b4a4c3ad-3261-49a7-8c6e-58a209f851b6', '20200326060054', 'root', '2020-03-26 18:00:54.355', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c6cf3b65-e250-4665-9d54-c7bd6b1e2a16', '20200326055816', 'root', '2020-03-26 17:58:16.119', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('c5681446-81cd-4c40-8436-4d1b176db0b4', '20200326055816', 'root', '2020-03-26 17:58:16.14', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('afc6ed50-2a08-4e64-b1b8-fc6d49aa4e65', '20200326060057', 'root', '2020-03-26 18:00:57.665', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('80c4b076-2ce2-4822-9d2c-987382bd13f4', '20200326060100', 'root', '2020-03-26 18:01:00.098', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b1f55ec8-e126-454a-a7cf-d1f7c791f060', '20200326060101', 'root', '2020-03-26 18:01:01.404', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1364a893-0dff-4fd2-811e-45581404c6f6', '20200326060104', 'root', '2020-03-26 18:01:04.652', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9f5a9d51-4ee8-4497-b692-5a049e185656', '20200326060815', 'root', '2020-03-26 18:08:15.208', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('63438e34-e4e4-43fb-8506-e996c4ddd888', '20200326061053', 'root', '2020-03-26 18:10:53.958', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('0c1bfc02-98df-427c-9252-aa4d3f413878', '20200326061054', 'root', '2020-03-26 18:10:54.487', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('19bbd7dc-26d4-44b5-9e7a-1c683cbc69ba', '20200326061332', 'root', '2020-03-26 18:13:32.989', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6581a2ff-5152-4a2a-8608-f225789eabbf', '20200326061516', 'root', '2020-03-26 18:15:16.208', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9b165d1e-f7a0-4ddf-aecc-545b53eef54f', '20200326061241', 'root', '2020-03-26 18:12:41.469', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('2eb9159b-b8ed-4d06-b8b1-420892829bf0', '20200326061242', 'root', '2020-03-26 18:12:42.005', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b607da57-715e-42cf-8a04-b4e93ecb545d', '20200326061520', 'root', '2020-03-26 18:15:20.497', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('7caf7b0f-797b-4add-909f-a75b59edff3f', '20200326061523', 'root', '2020-03-26 18:15:23.35', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('76e5ee49-d902-45f1-88d9-8e999a75da02', '20200326061524', 'root', '2020-03-26 18:15:24.771', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('aa6d5f14-8a6a-4a20-a3e0-684518c296ef', '20200326061525', 'root', '2020-03-26 18:15:25.968', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('03974dc5-a772-45f3-8c60-6fb353c7f835', '20200326061527', 'root', '2020-03-26 18:15:27.791', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('2238107c-d072-4af4-9445-1b1a48c75c3c', '20200326061527', 'root', '2020-03-26 18:15:27.939', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b0678936-0eaa-4845-8d24-c927573d638b', '20200326061529', 'root', '2020-03-26 18:15:29.691', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('58a44278-ce8a-4ec6-ae47-d10ca6d5fa99', '20200326061529', 'root', '2020-03-26 18:15:29.826', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('510257a6-b541-44c0-b7f2-779a99531d72', '20200326061256', 'root', '2020-03-26 18:12:56.122', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('52add0bf-03b3-4a96-9113-758593495306', '20200326061301', 'root', '2020-03-26 18:13:01.194', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('1df66706-5732-4fb0-9612-03fa2a454fad', '20200326061539', 'root', '2020-03-26 18:15:39.673', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('81c6ce09-de7a-4bdf-b28e-45488b0ec363', '20200326061541', 'root', '2020-03-26 18:15:41.144', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('610f6da7-7136-4c2a-82a0-0da7e09aa6fa', '20200326061542', 'root', '2020-03-26 18:15:42.88', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('35f42c0e-2379-4c28-93b9-5d4ffb568def', '20200326061544', 'root', '2020-03-26 18:15:44.149', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('013cad1e-c580-4b37-9872-2bb8238bb96c', '20200326061545', 'root', '2020-03-26 18:15:45.46', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f61e0554-1ebc-45e1-b991-026008869ce9', '20200326061545', 'root', '2020-03-26 18:15:45.601', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('5ac440fa-d40b-4cef-a4e1-da63591e36cd', '20200326061547', 'root', '2020-03-26 18:15:47.416', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a45dda1d-fa29-4680-b6e5-e203196ccfe3', '20200326061547', 'root', '2020-03-26 18:15:47.553', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('00fc29a4-1171-4311-a8c2-8f89c045f521', '20200327092630', 'root', '2020-03-27 09:26:30.025', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('2224c12c-1439-4922-95a0-601ff09f2b67', '20200327092630', 'root', '2020-03-27 09:26:30.041', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('5d680c83-eae5-4ffc-b12f-3f52a799717c', '20200327092752', 'root', '2020-03-27 09:27:52.819', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('073ee676-69a7-4937-b5b5-93637ec9b134', '20200327092753', 'root', '2020-03-27 09:27:53.33', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('3732a676-60a2-4943-81cd-c566820b4005', '20200327093032', 'root', '2020-03-27 09:30:32.462', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('54f062bb-c624-44e3-b97d-9b4f8e2535fc', '20200327093037', 'root', '2020-03-27 09:30:37.314', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('5daecb5e-e273-4186-879a-68ec9ebf986a', '20200327093045', 'root', '2020-03-27 09:30:45.581', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c7de3700-1215-4d8d-9074-8ea99ceff0ca', '20200327093045', 'root', '2020-03-27 09:30:45.728', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8e68e796-6fd4-41b4-b57d-6b32a1d9fcf3', '20200327093049', 'root', '2020-03-27 09:30:49.102', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('be7e37f6-2082-4f2f-be32-eb36cc6ce130', '20200327093050', 'root', '2020-03-27 09:30:50.4', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('669591b4-976f-466f-b3c5-edf46ff72d26', '20200327093052', 'root', '2020-03-27 09:30:52.594', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('20b1cc6c-1eb4-4b42-b0d7-e25f4533b70c', '20200327093052', 'root', '2020-03-27 09:30:52.759', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('fdc458ef-cc78-40d9-bcd7-5b211a78b635', '20200327093053', 'root', '2020-03-27 09:30:53.82', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('5dd117c8-4dc3-4e2b-a437-9debd15bd8eb', '20200327093053', 'root', '2020-03-27 09:30:53.949', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ca94a9fc-4111-4a27-b618-3b4adc6bd51b', '20200327093056', 'root', '2020-03-27 09:30:56.663', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c3d7d691-14a2-4799-ad0b-daf680b6440c', '20200327093057', 'root', '2020-03-27 09:30:57.567', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('22ab5f6c-9f5c-4f41-996b-602eca831ec2', '20200327093057', 'root', '2020-03-27 09:30:57.704', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('00ae9437-ec31-4ebd-a228-3290c67fb041', '20200327093058', 'root', '2020-03-27 09:30:58.959', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('877e6210-5b5b-49e3-868d-c496cbe8ec20', '20200327093100', 'root', '2020-03-27 09:31:00.074', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('90fe7c0a-34a1-46dd-9466-3b2693d896bf', '20200327093101', 'root', '2020-03-27 09:31:01.944', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('686de704-ed4f-4b8e-a63f-9e544658fa65', '20200327093103', 'root', '2020-03-27 09:31:03.365', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('cb262160-269e-4862-bf55-e27b52cf5659', '20200327093103', 'root', '2020-03-27 09:31:03.489', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1a9bd3d0-478d-4697-b82a-474ed1ec9683', '20200327092842', 'root', '2020-03-27 09:28:42.876', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b049f472-7204-4040-9c7a-31eec0cfe7d0', '20200327092843', 'root', '2020-03-27 09:28:43.413', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('0ecfe75a-5205-46f3-9890-25e5a0051a1c', '20200327093122', 'root', '2020-03-27 09:31:22.196', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('2733ad4b-1112-4b58-9153-5c2813698eb2', '20200327093130', 'root', '2020-03-27 09:31:30.196', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('edc01482-01be-4127-a976-749e79e5a7ba', '20200327093239', 'root', '2020-03-27 09:32:39.373', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1c1767a7-4ff3-41b6-b2f2-873eb1df8d29', '20200327093326', 'root', '2020-03-27 09:33:26.306', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f8481b04-6de4-40ad-8d8c-e8568f8f3001', '20200327093203', 'anonymousUser', '2020-03-27 09:32:03.655', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('f29909a8-bac7-4950-a7e7-1ed65ea1832b', '20200327095540', 'root', '2020-03-27 09:55:40.863', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c2a482b8-08d3-4a95-b511-e6fed3168194', '20200327095307', 'root', '2020-03-27 09:53:07.419', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('a64b432b-86eb-430b-89bc-517da44d221f', '20200327095307', 'root', '2020-03-27 09:53:07.878', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('cbf728b4-6426-49fa-ad85-6ae2894e69f0', '20200327095546', 'root', '2020-03-27 09:55:46.693', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('99e97dfd-4a74-4f43-a090-a2f6d332830a', '20200327095548', 'root', '2020-03-27 09:55:48.315', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ab9dce30-3ad6-43bd-921c-80a3e03a1088', '20200327095550', 'root', '2020-03-27 09:55:50.092', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('84648a6d-0009-45c9-b0a9-fc8915d04924', '20200327095551', 'root', '2020-03-27 09:55:51.179', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('60d3efa1-bdc3-4f27-82c7-1ce6d0f4fd4f', '20200327095552', 'root', '2020-03-27 09:55:52.077', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('5f6ee5d4-d238-419d-a48d-f4456dbd31a8', '20200327095552', 'root', '2020-03-27 09:55:52.22', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('801584f2-6cb8-46fc-9c2b-f4ab3076bd82', '20200327095553', 'root', '2020-03-27 09:55:53.516', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('5ed436f0-b261-48bd-bd89-9fb9cfb8d1e0', '20200327095553', 'root', '2020-03-27 09:55:53.654', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('45d524e0-6bba-4b6d-bb59-40e881a57cba', '20200327095555', 'root', '2020-03-27 09:55:55.905', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('d924ed79-f0a9-4c39-91d2-60d84585c48a', '20200327095556', 'root', '2020-03-27 09:55:56.036', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('079ecd78-d1cf-42eb-8015-0cb14b90166e', '20200327095557', 'root', '2020-03-27 09:55:57.688', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('24ee6cc6-73ed-43a9-8eae-c238d6c78073', '20200327095558', 'root', '2020-03-27 09:55:58.899', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('365da049-1260-4cd8-b278-6b37d04044fd', '20200327095600', 'root', '2020-03-27 09:56:00.275', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f6ee75ec-920b-45d1-bfc4-5749cc75a605', '20200327095601', 'root', '2020-03-27 09:56:01.196', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b7c4ea8a-fe79-47e3-a817-192afcedbeaa', '20200327095601', 'root', '2020-03-27 09:56:01.342', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e1469283-5bc5-45f3-a282-9da949e079d0', '20200327095602', 'root', '2020-03-27 09:56:02.869', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9eaf77e6-16ef-46ef-ab78-17b1f2ba928f', '20200327095603', 'root', '2020-03-27 09:56:03.012', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e60898c7-ff99-435e-9fd1-1233c40f17c7', '20200327095604', 'root', '2020-03-27 09:56:04.223', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c1c39755-3086-4340-a1c8-4132e99f5184', '20200327095608', 'root', '2020-03-27 09:56:08.69', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('909d22fb-ddfe-4aa4-885c-02bf3fef5cff', '20200327095609', 'root', '2020-03-27 09:56:09.918', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('71a5b6d7-7686-400e-89f9-39fc5e1b0715', '20200327095610', 'root', '2020-03-27 09:56:10.04', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('702b18ee-3238-4f1a-a02e-24a61e00798e', '20200327095611', 'root', '2020-03-27 09:56:11.947', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('fc9d3796-cdca-48b3-afca-4d226b97e3fd', '20200327095613', 'root', '2020-03-27 09:56:13.557', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8d40ddd9-5dea-4470-b10a-ec71d8517b4e', '20200327095614', 'root', '2020-03-27 09:56:14.66', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('bf59b967-ac20-4306-b0ff-a0a1b5ba0035', '20200327095614', 'root', '2020-03-27 09:56:14.789', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('05d7d5c7-9f5a-406a-9edf-a477e9654504', '20200327095615', 'root', '2020-03-27 09:56:15.874', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('51af5b16-7e8a-4b35-8d1d-e3426390b633', '20200327095616', 'root', '2020-03-27 09:56:16.005', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('3e36e357-511c-4481-adfc-f7b05d238ba3', '20200327095618', 'root', '2020-03-27 09:56:18.825', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6cd4ca18-5d57-4eb5-9ce3-2ffdcf6b04fe', '20200327095619', 'root', '2020-03-27 09:56:19.167', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('52d4be7d-8dd6-48ef-82d3-0beef0e52921', '20200327095619', 'root', '2020-03-27 09:56:19.172', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6d0b6ec3-dc60-418a-a09f-ff43670d81cd', '20200327095619', 'root', '2020-03-27 09:56:19.421', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('7b162a59-76a7-4afa-a39b-a4677d577115', '20200327095621', 'root', '2020-03-27 09:56:21.903', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f86243be-0a09-4070-8638-4c6690d2dc16', '20200327095622', 'root', '2020-03-27 09:56:22.055', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6397c296-d87d-484e-870e-c0fd276ac4a3', '20200327095623', 'root', '2020-03-27 09:56:23.326', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a0ec6316-2419-4a93-a863-aa3f66dbbdd8', '20200327095624', 'root', '2020-03-27 09:56:24.854', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('05488128-acd8-4ddc-9333-8b894a2cd7a9', '20200327095626', 'root', '2020-03-27 09:56:26.18', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('66cf1ef8-6c40-420c-ab5e-e5d484305f6f', '20200327095627', 'root', '2020-03-27 09:56:27.271', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('31802dbc-de17-47ae-b58c-9f6ef9f9637a', '20200327095628', 'root', '2020-03-27 09:56:28.207', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9140ce21-8059-4a88-b204-07d7507a7300', '20200327095628', 'root', '2020-03-27 09:56:28.392', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('7bee251d-4cad-470e-beb6-b7487deff9a0', '20200327095630', 'root', '2020-03-27 09:56:30.017', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e0343c2e-4fe6-4f63-ab90-869e2076afc4', '20200327095630', 'root', '2020-03-27 09:56:30.153', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b0660f2e-b6c4-4a51-a3f9-7f022d3298da', '20200327095632', 'root', '2020-03-27 09:56:32.206', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('87e3ccac-cfa7-4f74-83c2-688a3c0de9c5', '20200327095632', 'root', '2020-03-27 09:56:32.364', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('48c52c6f-8596-46bd-8cc8-52055c737352', '20200327095633', 'root', '2020-03-27 09:56:33.624', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('2e80d1a3-1353-4f64-ac0a-f20dd8102999', '20200327095633', 'root', '2020-03-27 09:56:33.767', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('68101d8f-cb53-4a98-901d-0f96b4611fc1', '20200327095737', 'root', '2020-03-27 09:57:37.763', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8e52ef79-281d-484a-84c4-75cdd7c1c42e', '20200327095925', 'root', '2020-03-27 09:59:25.755', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('2b501117-28ce-4116-82ef-e249f469b0d4', '20200327100346', 'root', '2020-03-27 10:03:46.472', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('297709f1-2613-49df-bea9-4075f9689e94', '20200327100730', 'root', '2020-03-27 10:07:30.132', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('7f60a3fc-0d82-4cbb-8009-176cb8798c69', '20200327100822', 'root', '2020-03-27 10:08:22.88', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('24ea2027-bc85-4ef4-aec6-64378cf6ff8d', '20200327100936', 'root', '2020-03-27 10:09:36.443', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('456c411f-c9e7-42f2-8e2f-c4c30a359585', '20200327100940', 'root', '2020-03-27 10:09:40.431', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('78c2cd76-e7b7-4908-943e-aebe8efe9a7d', '20200327100941', 'root', '2020-03-27 10:09:41.656', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('27c159b5-f82f-4618-9b5c-61f68d8c58f6', '20200327100703', 'anonymousUser', '2020-03-27 10:07:03.27', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('537e1a27-2c55-4e70-af6b-28929bb518bc', '20200327100942', 'root', '2020-03-27 10:09:42.942', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('08a50f9c-a817-4afd-ad83-16618e793ba1', '20200327100944', 'root', '2020-03-27 10:09:44.042', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('be9444eb-3608-4610-b589-2b3620eeb465', '20200327100944', 'root', '2020-03-27 10:09:44.175', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('dbc57462-ec81-469e-a01c-7d691833e595', '20200327100945', 'root', '2020-03-27 10:09:45.151', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9991ac16-365b-430d-8976-3a023f7cf427', '20200327100945', 'root', '2020-03-27 10:09:45.291', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('81b28ffb-d02d-43a9-a68c-46de1ac42112', '20200327100946', 'root', '2020-03-27 10:09:46.301', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('0da0eb58-ba6c-4cb4-9abd-82469c9c1e33', '20200327100946', 'root', '2020-03-27 10:09:46.431', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9b274f4f-a798-4a52-a297-1428b33b71b2', '20200327101305', 'root', '2020-03-27 10:13:05.868', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('0b905361-ec01-4bf7-b8dd-e664c3bd097b', '20200327101318', 'root', '2020-03-27 10:13:18.892', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('350aefda-b42e-4d8a-8a04-d799230afe11', '20200327101324', 'root', '2020-03-27 10:13:24.094', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e53dce3d-05bd-4125-9335-1466d6334afa', '20200327101326', 'root', '2020-03-27 10:13:26.192', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('5e5e31bf-2238-4969-bb77-fe27bc5cea19', '20200327101802', 'root', '2020-03-27 10:18:02.325', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('631fff46-4b72-4fd9-afe9-be964359d77d', '20200327101537', 'root', '2020-03-27 10:15:37.715', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('5ac22ec6-ac03-4f83-8a79-3d60a039440c', '20200327101538', 'root', '2020-03-27 10:15:38.071', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('63ecd91e-cc9a-44dd-bad2-88efa18289c8', '20200327101817', 'root', '2020-03-27 10:18:17.036', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('120f1dc8-733f-4f37-81e4-238b67396d4d', '20200327101819', 'root', '2020-03-27 10:18:19.052', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('eb1e5807-58b2-4814-b0b8-117848accb1b', '20200327101820', 'root', '2020-03-27 10:18:20.637', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8f3f62f0-535e-4537-b57e-7c4076eb969b', '20200327101821', 'root', '2020-03-27 10:18:21.605', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e22be82e-c572-49ef-afce-94cf78b60113', '20200327101821', 'root', '2020-03-27 10:18:21.714', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a86e275c-6b11-4b0c-9298-62ab087a5a5c', '20200327101823', 'root', '2020-03-27 10:18:23.021', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f8d768b8-24d5-4327-8183-6b1e1e2772f4', '20200327101823', 'root', '2020-03-27 10:18:23.157', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a984bf67-2779-495c-a6b7-5c85e9b1a78f', '20200327101826', 'root', '2020-03-27 10:18:26.707', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b210101a-6504-4a32-8387-3b2e78a20b0d', '20200327101828', 'root', '2020-03-27 10:18:28.842', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6f6c3bac-5965-4532-b17a-4218574955d2', '20200327101828', 'root', '2020-03-27 10:18:28.955', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('358ea95c-2293-4469-87c3-bf673286fc75', '20200327101831', 'root', '2020-03-27 10:18:31.963', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('028e37f5-7e9c-429b-b85d-152241d0f726', '20200327101832', 'root', '2020-03-27 10:18:32.117', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a3d68da3-956b-402a-8ab4-214522204520', '20200327101554', 'root', '2020-03-27 10:15:54.337', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('0b4ade27-2ad2-4b5e-8556-38607d144ed0', '20200327101554', 'root', '2020-03-27 10:15:54.751', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('33906248-080d-46db-a4b9-75dd5a206242', '20200327101833', 'root', '2020-03-27 10:18:33.524', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('3ecea8fa-185c-481c-bcb7-ab930c8a7cbf', '20200327101836', 'root', '2020-03-27 10:18:36.684', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('17b519c2-3cd2-4845-99cc-7825e0bd7178', '20200327101921', 'root', '2020-03-27 10:19:21.27', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('478336b3-49ee-4b93-bdc5-a83f8a378aa1', '20200327102028', 'root', '2020-03-27 10:20:28.716', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('dfea3b32-ac76-4aee-b645-f29b8227bd21', '20200327102033', 'root', '2020-03-27 10:20:33.571', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4ee8a730-d4f2-4878-8a28-77ebf1183b2c', '20200327102035', 'root', '2020-03-27 10:20:35.212', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('aa4b888f-51c8-4a0b-9fcd-665a23f85afc', '20200327102035', 'root', '2020-03-27 10:20:35.328', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4b170b58-815b-4eff-a8bf-c7cd47fb677b', '20200327102036', 'root', '2020-03-27 10:20:36.698', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b3138d63-41d4-472f-89f6-a4d7a731eb57', '20200327102037', 'root', '2020-03-27 10:20:37.893', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9d635d0c-e784-4a7a-9fc0-2f0afeeaedae', '20200327102039', 'root', '2020-03-27 10:20:39.931', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4f46bb77-51e3-4e68-9f84-247334692410', '20200327102040', 'root', '2020-03-27 10:20:40.059', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('2a295af9-0eeb-42c3-929d-e6760b7ae28b', '20200327102040', 'root', '2020-03-27 10:20:40.895', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('7774255f-2c7b-4233-86ef-bc84bfeb67b1', '20200327102041', 'root', '2020-03-27 10:20:41.033', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('cad48754-b1f3-46bd-983c-a69eca27ef40', '20200327102045', 'root', '2020-03-27 10:20:45.565', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('af7c0b81-afd5-442d-8f6b-a6f3641445d8', '20200327102047', 'root', '2020-03-27 10:20:47.489', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('82d1bcd0-f078-4c33-9a80-900d6ab090ff', '20200327102047', 'root', '2020-03-27 10:20:47.602', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c06b7a6b-fa5f-42a2-b521-bea68e48fabf', '20200327102048', 'root', '2020-03-27 10:20:48.741', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9683b1fe-8415-498e-9f21-58a6f9394728', '20200327102050', 'root', '2020-03-27 10:20:50.574', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('d6b1b283-63f7-40fc-a8a8-906b3f28c02f', '20200327102052', 'root', '2020-03-27 10:20:52.008', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('071c8f72-2066-4b68-a52a-e7177d4ce27c', '20200327102053', 'root', '2020-03-27 10:20:53.449', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('167ca95e-c87b-4a7e-8122-89fff4043c87', '20200327102053', 'root', '2020-03-27 10:20:53.585', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9ba5d8e6-b1eb-43be-8ee7-458817efb7e9', '20200327102054', 'root', '2020-03-27 10:20:54.552', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e1da3958-de0a-4dcb-948e-14071b0f44e4', '20200327102054', 'root', '2020-03-27 10:20:54.683', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('051db413-3b4c-4e20-ad3c-a67f1ab09b74', '20200327102055', 'root', '2020-03-27 10:20:55.643', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f821997f-0c85-4ebb-aa7f-b9a96423f6cc', '20200327102055', 'root', '2020-03-27 10:20:55.767', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('059a3759-5e6e-4f58-8887-1ab917df0fef', '20200327102012', 'anonymousUser', '2020-03-27 10:20:12.58', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('4ed1d9b9-cf26-48d8-915d-be2c36a889cd', '20200327102344', 'root', '2020-03-27 10:23:44.415', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1cc33df9-a4c1-48c5-9d25-2cf78461f8fe', '20200327102703', 'root', '2020-03-27 10:27:03.166', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f3ff441c-8c1e-4400-904f-667a7f9f0cac', '20200327102745', 'root', '2020-03-27 10:27:45.797', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('608eddf1-674e-4db7-b622-f653ce6e546d', '20200327102544', 'root', '2020-03-27 10:25:44.104', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('906d0c3f-6823-4ab0-aee5-2fbb77c03911', '20200327102544', 'root', '2020-03-27 10:25:44.145', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('70be6b80-ca7d-455d-b1d5-9e7b7e846c7b', '20200327102822', 'root', '2020-03-27 10:28:22.968', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b37610d1-166b-479b-a29f-1220f9a268ff', '20200327103202', 'root', '2020-03-27 10:32:02.669', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('31cae8a8-86ab-48bc-9462-43b092bc43ca', '20200327103014', 'root', '2020-03-27 10:30:14.907', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('1c7c6984-cbfa-4ee9-8dde-f3f115aaf545', '20200327103014', 'root', '2020-03-27 10:30:14.915', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('a5885eee-a20e-446d-9d88-5e08c2ba1eb2', '20200327103253', 'root', '2020-03-27 10:32:53.677', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4bd39eee-d311-4be2-a0e0-0336b5c24297', '20200327103631', 'root', '2020-03-27 10:36:31.941', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('3f1d659e-0597-4af1-b498-4a1802c7f4a6', '20200327103636', 'root', '2020-03-27 10:36:36.897', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a2e739cd-5f82-47c7-8ed6-55a409b84e19', '20200327103410', 'root', '2020-03-27 10:34:10.725', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('14f59518-a184-46c2-8163-6bbac5586b51', '20200327103411', 'root', '2020-03-27 10:34:11.385', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('2f73215a-1f09-413b-bf9a-10ca481909c9', '20200327103650', 'root', '2020-03-27 10:36:50.168', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('0021f5fb-35bd-455a-b7a2-58020c89cc24', '20200327103739', 'root', '2020-03-27 10:37:39.624', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a2655edf-0d2d-402e-b8ea-e51215afcac9', '20200327103502', 'root', '2020-03-27 10:35:02.191', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('d6defca6-99cf-4d59-811e-f75881061690', '20200327103502', 'root', '2020-03-27 10:35:02.879', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('95db2cfe-a2d0-4552-a080-5479556b20d9', '20200327103741', 'root', '2020-03-27 10:37:41.659', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('eba9c53a-4679-42d0-9850-fdabe31406f3', '20200327103646', 'anonymousUser', '2020-03-27 10:36:46.345', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('f25fa2c5-fd76-4d5f-93e7-e52bb4017066', '20200327103743', 'anonymousUser', '2020-03-27 10:37:43.44', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('be6a46ef-6f88-4929-8d52-7bba192a2d56', '20200327104230', 'root', '2020-03-27 10:42:30.791', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('d5f5a83d-b20a-437a-af95-6275eac00ec9', '20200327104344', 'root', '2020-03-27 10:43:44.719', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('0ab68c6a-66a5-4af4-9c97-1eacfa5e274e', '20200327104222', 'anonymousUser', '2020-03-27 10:42:22.891', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('6513361b-9bf9-4e5b-ade2-274e18208f0d', '20200327104610', 'root', '2020-03-27 10:46:10.414', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e3deb64b-d999-4f74-a025-4aaa8f0c32a8', '20200327104340', 'anonymousUser', '2020-03-27 10:43:40.196', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('13d89db7-84e6-48e0-a64a-4a865bea33d2', '20200327104556', 'anonymousUser', '2020-03-27 10:45:56.337', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('894b42ef-4c38-4ef1-973f-ebc3f212f3ef', '20200327104951', 'anonymousUser', '2020-03-27 10:49:51.985', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('de19f005-667a-4d72-b707-4fa68665ca29', '20200327105441', 'anonymousUser', '2020-03-27 10:54:41.29', '获取office配置', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('8332709d-a61f-4b28-8eb3-d8afe915bf0f', '20200327105537', 'anonymousUser', '2020-03-27 10:55:37.795', '获取office配置', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('246cfe28-95fc-4f83-aff5-ee25bf5b2dbe', '20200327105835', 'root', '2020-03-27 10:58:35.134', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8548580f-6a7d-4eef-8bef-0193fa06f801', '20200327105839', 'root', '2020-03-27 10:58:39.667', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('616d9b8b-37dc-42a8-86a3-9e5472050fa6', '20200327105846', 'root', '2020-03-27 10:58:46.805', '新增文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('90f2bfe8-e512-46b7-87cb-215e75ab68d5', '20200327105846', 'root', '2020-03-27 10:58:46.94', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a792843c-396d-4794-abd0-07d8686127ae', '20200327105854', 'root', '2020-03-27 10:58:54.991', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('46b7d66b-bb4d-442d-ad6c-8d1bc51ec7dc', '20200327105906', 'root', '2020-03-27 10:59:06.852', '删除文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f7b56689-d7ea-40e7-ad1e-bba0c7730e70', '20200327105701', 'anonymousUser', '2020-03-27 10:57:01.228', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('d8692757-bf4a-47e9-ab4f-099fed611f23', '20200327110115', 'root', '2020-03-27 11:01:15.358', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c4a1cdcd-f0e9-4d62-9aed-2d98e6de7d0b', '20200327110120', 'root', '2020-03-27 11:01:20.804', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e142866b-017b-4670-aeec-6906b1e4e491', '20200327105842', 'root', '2020-03-27 10:58:42.211', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('1f0f402d-2f4d-4249-b85d-f3ad3a5d96ac', '20200327105842', 'root', '2020-03-27 10:58:42.216', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('47bfd341-5f97-450e-8e47-5605c5dc6af7', '20200327105956', 'anonymousUser', '2020-03-27 10:59:56.892', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('282da782-0bfe-48ef-ac14-7b72a387a55f', '20200327110024', 'anonymousUser', '2020-03-27 11:00:24.296', '文件上传', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('623f6873-5a2d-4497-8aa0-56f8b105899c', '20200327110401', 'anonymousUser', '2020-03-27 11:04:01.44', '获取office配置', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('f5d896f0-2116-4486-b14b-9a13c41d3402', '20200327110759', 'anonymousUser', '2020-03-27 11:07:59.707', '获取office配置', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('b9f0dc65-89c9-48a6-8383-48ad49933a90', '20200327110816', 'anonymousUser', '2020-03-27 11:08:16.417', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('21da2cda-6c97-43c2-a539-0f6e5a5285a5', '20200327110834', 'anonymousUser', '2020-03-27 11:08:34.397', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('26f43ee0-a8ef-4c56-a7bc-cee8b99643e9', '20200327110840', 'anonymousUser', '2020-03-27 11:08:40.748', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('3a4f2d6e-9808-4317-af8d-7683f91bc6aa', '20200327111210', 'anonymousUser', '2020-03-27 11:12:10.765', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('a126a000-a273-4f2b-88a3-365b5e9b918b', '20200327111313', 'anonymousUser', '2020-03-27 11:13:13.421', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('19bf063d-c8eb-4bff-a04c-191fff9c4171', '20200327111411', 'anonymousUser', '2020-03-27 11:14:11.509', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b1c2c453-0808-4f81-967a-546a99d81075', '20200327111421', 'root', '2020-03-27 11:14:21.811', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('573ca4ed-e8dc-4ec4-a95d-ee85fa3abb34', '20200327111422', 'anonymousUser', '2020-03-27 11:14:22.751', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('9833fd09-0e7c-4d49-8dda-5bc398a55a05', '20200327111704', 'root', '2020-03-27 11:17:04.727', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8906b9e7-bbbb-466e-afd1-af05e60e2aa6', '20200327111425', 'root', '2020-03-27 11:14:25.883', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('1b4abb8d-3006-45b9-8b5b-79206d4bb848', '20200327111433', 'anonymousUser', '2020-03-27 11:14:33.586', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('a5b32d3d-b47f-494d-97f8-3d1e4c826566', '20200327111443', 'anonymousUser', '2020-03-27 11:14:43.481', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('13145be8-522a-4487-962b-7858c4c7a32a', '20200327111452', 'anonymousUser', '2020-03-27 11:14:52.278', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('f5078053-2e1e-40d5-a8cf-28bb1aa85773', '20200327111526', 'anonymousUser', '2020-03-27 11:15:26.466', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('a8af324d-3a37-4041-bb4b-55280f8a0e5f', '20200327111716', 'root', '2020-03-27 11:17:16.132', '删除文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('01e77cb6-a074-4d1d-8dd3-c8eb958b4f21', '20200327111716', 'root', '2020-03-27 11:17:16.287', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('37cccc5d-d1c5-4c79-a1cb-b3876d48cd2e', '20200327111727', 'root', '2020-03-27 11:17:27.086', '删除文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6b63f94d-ae17-4a20-9189-57b7b268b74b', '20200327111727', 'root', '2020-03-27 11:17:27.214', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('45c85691-6a36-4343-a497-b5244a3d47ed', '20200327111733', 'root', '2020-03-27 11:17:33.045', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6b7dc42a-e096-4e86-8628-1c79d1e84f59', '20200327111743', 'root', '2020-03-27 11:17:43.943', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('775eb40b-47c0-4873-a441-9f0d6c13e5f4', '20200327111746', 'root', '2020-03-27 11:17:46.401', '新增文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('7944d3fe-c19d-4db8-825b-416fcd15799f', '20200327111746', 'root', '2020-03-27 11:17:46.535', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('092377fb-43e9-4208-a174-edb93b265003', '20200327111754', 'root', '2020-03-27 11:17:54.444', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('709fb979-6714-43d5-bda4-08b36b229d71', '20200327111911', 'root', '2020-03-27 11:19:11.887', '新增文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f29c9dd8-8ee9-4a68-b22f-31f20a6983c3', '20200327111912', 'root', '2020-03-27 11:19:12.011', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9a6da208-6ba6-4507-b92c-9f2efce8911a', '20200327111916', 'root', '2020-03-27 11:19:16.191', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('397a413b-815d-4d91-b199-ab7223ccd6d2', '20200327112022', 'root', '2020-03-27 11:20:22.345', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('02bc2123-65c7-4e62-b0b9-947c5cbb6087', '20200327112027', 'root', '2020-03-27 11:20:27.871', '新增文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('eb72d9ca-6f46-4de2-94b7-50443a8c549d', '20200327112028', 'root', '2020-03-27 11:20:28.02', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c4387932-0637-494f-a668-e094d80d2698', '20200327112033', 'root', '2020-03-27 11:20:33.291', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b7cd8aa6-8113-47aa-9422-9a42f5ee3c9a', '20200327112047', 'root', '2020-03-27 11:20:47.634', '新增文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1233f333-b92a-465e-bb22-743585c03e7e', '20200327112047', 'root', '2020-03-27 11:20:47.764', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('cd5f779e-b1b4-4101-85f0-ec789c4253fe', '20200327112053', 'root', '2020-03-27 11:20:53.289', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('3cd21bbe-aaa9-4360-8e60-1f3cce44a232', '20200327112056', 'root', '2020-03-27 11:20:56.589', '删除文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a23d34d5-58e7-47b0-af8a-69c7d8258fb7', '20200327112056', 'root', '2020-03-27 11:20:56.714', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e55dd392-c83e-4628-9763-f3b305e44ee1', '20200327112059', 'root', '2020-03-27 11:20:59.345', '删除文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('902828e9-ac72-4a73-8ceb-6a8816155a65', '20200327112059', 'root', '2020-03-27 11:20:59.487', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4f87913f-392f-4af9-b1f9-db8a2629da4d', '20200327112102', 'root', '2020-03-27 11:21:02.797', '删除文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('27b30365-4fdf-4382-b9fe-a815ad47596e', '20200327112102', 'root', '2020-03-27 11:21:02.943', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('713fa571-d435-4d58-8312-169715795b20', '20200327112105', 'root', '2020-03-27 11:21:05.528', '删除文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('eb470ce7-679f-4e04-a70a-3e9f0bd5a447', '20200327112105', 'root', '2020-03-27 11:21:05.654', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6d9ed844-0bd4-4a87-a88b-646f600295a9', '20200327111954', 'anonymousUser', '2020-03-27 11:19:54.166', '获取office配置', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('85021986-a366-4b8b-b412-be711b7e17f6', '20200327112000', 'anonymousUser', '2020-03-27 11:20:00.79', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('ea966bb8-25e4-4aa9-849a-0a79dc3bcd9a', '20200327112413', 'root', '2020-03-27 11:24:13.665', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ee15ac49-d31f-4674-8906-1944f9b3b956', '20200327112420', 'root', '2020-03-27 11:24:20.649', '新增文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('54bbb8a9-50f1-4898-8550-db6d654b93e6', '20200327112420', 'root', '2020-03-27 11:24:20.78', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('006abb8b-b6d5-4f46-85a7-f537210e78d5', '20200327112429', 'root', '2020-03-27 11:24:29.377', '修改文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1c287ae8-2f53-4855-86c5-03e5a8e7807d', '20200327112443', 'root', '2020-03-27 11:24:43.125', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('440b95c6-5007-4c0d-963f-ef0792834f3b', '20200327112525', 'anonymousUser', '2020-03-27 11:25:25.057', '获取office配置', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('c761cd83-8152-4246-84f4-7d96039ea2e5', '20200327112534', 'anonymousUser', '2020-03-27 11:25:34.053', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('51b18acb-5924-47be-ba14-8eaed9f63597', '20200327112612', 'anonymousUser', '2020-03-27 11:26:12.576', '编辑文档', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('e82ee288-8919-413c-b960-c50d9cb8930e', '20200327113348', 'root', '2020-03-27 11:33:48.395', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('bf383082-0a3d-4739-ab01-b587fe0a7096', '20200327113420', 'root', '2020-03-27 11:34:20.44', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6885d117-65c8-443e-a40c-9b975665d1f8', '20200327113522', 'root', '2020-03-27 11:35:22.621', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ae1b4ab8-7d5c-4f44-ac56-6ec415e8382c', '20200327113817', 'root', '2020-03-27 11:38:17.758', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('16bcd883-af3c-43d1-af16-e6f289aef0f4', '20200327114137', 'root', '2020-03-27 11:41:37.114', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8520520d-1a5c-4f08-bfe3-14e00868cfdf', '20200327113858', 'root', '2020-03-27 11:38:58.64', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('c68abcb3-b668-427e-9a79-c55aa526da66', '20200327113858', 'root', '2020-03-27 11:38:58.652', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('fcf4502d-3966-49fb-ae43-2cae9c39f537', '20200327114323', 'root', '2020-03-27 11:43:23.622', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f63bb3e6-6277-4210-a158-182dd7fbd9b1', '20200327114424', 'root', '2020-03-27 11:44:24.734', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('34f2182a-d66f-4c50-9172-c42e4d8f3a5e', '20200327114538', 'root', '2020-03-27 11:45:38.759', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('d608598f-d23c-4589-b7b0-9deb351ad3e9', '20200327115540', 'anonymousUser', '2020-03-27 11:55:40.087', '文件上传', NULL, '127.0.0.1');
INSERT INTO "public"."sys_log" VALUES ('0de07d65-506a-4169-a2ed-0c6bc69cb8fe', '20200327115552', 'root', '2020-03-27 11:55:52.94', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('1465ad65-c79f-429d-ab9b-f270e91a1a1d', '20200327115553', 'root', '2020-03-27 11:55:53.235', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('3ffc6350-0463-4d24-b15b-0723d5dd25b6', '20200327115832', 'root', '2020-03-27 11:58:32.061', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('618d5f29-45fb-4aac-9414-b990b2b75711', '20200327115606', 'anonymousUser', '2020-03-27 11:56:06.021', '获取office配置', NULL, '127.0.0.1');
INSERT INTO "public"."sys_log" VALUES ('3e7c40ce-d6f6-4135-aa3a-7f8de63768ad', '20200327115844', 'root', '2020-03-27 11:58:44.946', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('4b87b983-06e8-47b1-825f-1c5d6ab72c02', '20200327115845', 'root', '2020-03-27 11:58:45.177', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('343254e7-6729-439a-a3cf-c9b26d1676f2', '20200327120124', 'root', '2020-03-27 12:01:24.024', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('92158f76-2a23-45e3-9d69-b8da24bf1532', '20200327120509', 'root', '2020-03-27 12:05:09.943', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('32728934-c7f9-482d-b902-db1b8cd7ea74', '20200327120510', 'root', '2020-03-27 12:05:10.206', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('c5695874-cacc-4e29-af25-e81ca432f51d', '20200327120749', 'root', '2020-03-27 12:07:49.033', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f431f36d-55a7-4c11-8ae2-00c851d36f57', '20200327043824', 'root', '2020-03-27 16:38:24.087', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('53545979-0aa6-4cdb-ba18-58489b8cfd29', '20200327120520', 'root', '2020-03-27 12:05:20.925', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('e0402723-3d90-4b49-ae1a-e6dbf651a99b', '20200327120521', 'root', '2020-03-27 12:05:21.239', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('744c3089-e367-47a6-80d2-e0dec57bbbd9', '20200327121147', 'root', '2020-03-27 12:11:47.771', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('9d13be86-4b0e-4387-8afe-4e7bacef3a7e', '20200327121148', 'root', '2020-03-27 12:11:48.005', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('284dc60c-b4cc-4556-9756-781af380c653', '20200327120800', 'root', '2020-03-27 12:08:00.047', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('2f9d8dc9-5e16-445b-b242-372267d96f93', '20200327120846', 'root', '2020-03-27 12:08:46.855', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c8fdcd10-1fd3-4ca2-bd1e-cd670e8dd6bc', '20200327121016', 'root', '2020-03-27 12:10:16.465', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('cad842b5-199e-445e-a8c9-5630d76bf632', '20200327121130', 'root', '2020-03-27 12:11:30.225', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a760d6f1-55ef-4bc7-a920-f35baa35c5f7', '20200327121422', 'root', '2020-03-27 12:14:22.399', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('68a9bf9e-f31b-4b35-aa73-c34eaea13f04', '20200327121426', 'root', '2020-03-27 12:14:26.821', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('34c7a07e-0ba0-49c6-a215-71cb9c33cf0c', '20200327121452', 'root', '2020-03-27 12:14:52.462', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8fe9869f-2f5c-4b62-89e1-fdbd7dc861ed', '20200327125958', 'root', '2020-03-27 12:59:58.438', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ba2f6237-bd8f-4ca7-bf66-5e8c381a607d', '20200327010014', 'root', '2020-03-27 13:00:14.639', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('67c08574-637d-456b-ad10-d8877235ec46', '20200327010112', 'root', '2020-03-27 13:01:12.821', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4e7c1552-3f26-447e-b176-ba4a4f81148d', '20200327010146', 'root', '2020-03-27 13:01:46.739', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('afe117b0-babb-4974-9db7-8fa7175bb3f8', '20200327010200', 'root', '2020-03-27 13:02:00.708', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('13d3ff77-5db0-4e31-b875-8aaaf9232dce', '20200327010345', 'root', '2020-03-27 13:03:45.325', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('fdf765e5-cb94-4830-873a-d60c9d78ffa8', '20200327010409', 'root', '2020-03-27 13:04:09.513', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1cd6a4e1-d21e-4302-8f8d-c4862a4fd39a', '20200327010141', 'root', '2020-03-27 13:01:41.504', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('de860161-0be5-4519-9fd0-d432dafdf338', '20200327010141', 'root', '2020-03-27 13:01:41.515', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('ee05d9da-fcf0-4317-bcb5-9b798990cddb', '20200327010420', 'root', '2020-03-27 13:04:20.289', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f61b238a-f51b-4344-93c9-827fe51529f0', '20200327010422', 'root', '2020-03-27 13:04:22.062', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1adbc022-c754-40fe-9fe9-ffd0fbdb7376', '20200327010535', 'root', '2020-03-27 13:05:35.006', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('78a91cc9-98f5-4502-ba86-acdca40a3cc0', '20200327010604', 'root', '2020-03-27 13:06:04.126', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('32082c71-0d12-439b-a0b0-d65df72763c1', '20200327010608', 'root', '2020-03-27 13:06:08.027', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('385458c9-f14f-4fbd-ba16-3486e156c5ad', '20200327010333', 'root', '2020-03-27 13:03:33.888', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('0d4fca57-bee2-4d0d-a7a6-fa2d65f3b20c', '20200327010334', 'root', '2020-03-27 13:03:34.161', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('eb77606c-ed4b-4e64-9878-70258cfc3916', '20200327010613', 'root', '2020-03-27 13:06:13.004', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b8e59ec2-d7e9-4f62-bb4f-c7f242a9f671', '20200327010615', 'root', '2020-03-27 13:06:15.199', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('35dede36-0e92-4a67-a8ab-15cfd4ec9e9c', '20200327011618', 'root', '2020-03-27 13:16:18.219', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('e8ff17f7-c73e-42fe-a007-3d6f4e4ff92c', '20200327011618', 'root', '2020-03-27 13:16:18.429', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('aba34168-e0dc-4915-8ee1-8a434a7d29ef', '20200327011857', 'root', '2020-03-27 13:18:57.278', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('3200291f-5f91-4dae-baf5-d7b8826ff4b4', '20200327011859', 'root', '2020-03-27 13:18:59.122', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('940efad5-01ff-4fe4-a524-6d22eb5ffd02', '20200327011644', 'root', '2020-03-27 13:16:44.198', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('8de444e7-6a3f-4c07-9340-cc3e14779c68', '20200327011644', 'root', '2020-03-27 13:16:44.503', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('fc458560-9242-495c-aa83-7cf00a854b4e', '20200327011923', 'root', '2020-03-27 13:19:23.344', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('602d834c-72ab-4f71-b77e-c76f10fdd65f', '20200327011924', 'root', '2020-03-27 13:19:24.658', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('72eb10aa-3b5a-47b3-9f99-40c72041a19a', '20200327011726', 'root', '2020-03-27 13:17:26.672', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('86705b3e-b35b-408b-a02b-22129d8b6884', '20200327011726', 'root', '2020-03-27 13:17:26.934', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('7306e443-00af-4f60-b410-44942e76d11d', '20200327012005', 'root', '2020-03-27 13:20:05.778', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('d84102d3-6dd7-4a63-9d13-4b848c730b6c', '20200327012006', 'root', '2020-03-27 13:20:06.679', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('2f5b65a3-e3e0-4095-a523-eed1af7352ca', '20200327011838', 'root', '2020-03-27 13:18:38.91', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('cd8739e6-ae66-4a2e-ba0b-b89bfb70c182', '20200327011839', 'root', '2020-03-27 13:18:39.167', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('87bcbef0-a95b-4e9f-ada2-db8e5014ffe0', '20200327012118', 'root', '2020-03-27 13:21:18.038', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ec5313f2-fdc6-4648-844b-0eb9b0b470db', '20200327012119', 'root', '2020-03-27 13:21:19.348', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b86c15e9-45c7-41b1-89a4-93012ac6f39c', '20200327012031', 'root', '2020-03-27 13:20:31.179', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('21451ff5-3016-4b03-ac46-83a417343764', '20200327012031', 'root', '2020-03-27 13:20:31.395', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('5cc4b6a8-1f86-470b-a99b-57ef4394ee72', '20200327012310', 'root', '2020-03-27 13:23:10.238', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8581d4c3-6020-4d44-8736-37b70a50dfc3', '20200327012311', 'root', '2020-03-27 13:23:11.064', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('07ef6020-00eb-4b2f-b2a4-31e37128b35b', '20200327012237', 'root', '2020-03-27 13:22:37.268', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('a6ad043b-8a11-4d17-a96e-48071a0b89e1', '20200327012237', 'root', '2020-03-27 13:22:37.507', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('674962a7-bb3d-4266-b434-c3ab33be189b', '20200327012516', 'root', '2020-03-27 13:25:16.353', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b84b2653-45bf-4b59-a69f-00d0e336368f', '20200327012517', 'root', '2020-03-27 13:25:17.528', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c7cf77e6-83a4-427b-a501-1f93da519b43', '20200327012247', 'root', '2020-03-27 13:22:47.986', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('89a30357-5695-4c86-bbed-0cd630a1fa4c', '20200327012248', 'root', '2020-03-27 13:22:48.287', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('13c5e16f-23fb-4916-aae2-819660b86297', '20200327012527', 'root', '2020-03-27 13:25:27.111', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('7cd80814-3ad7-4720-9e14-abf35682bdbf', '20200327012528', 'root', '2020-03-27 13:25:28.14', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('31b80454-b39b-4bb7-9812-91bd45ff4c5b', '20200327020224', 'root', '2020-03-27 14:02:24.646', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('801d8e9b-bb0d-4cea-9ab6-a71090913ef2', '20200327020228', 'root', '2020-03-27 14:02:28.23', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ee727edb-8e5e-419d-b27a-5c316896fe76', '20200327015954', 'anonymousUser', '2020-03-27 13:59:54.568', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('7e312a04-294b-4712-a92f-d80456bbde3e', '20200327020001', 'anonymousUser', '2020-03-27 14:00:01.029', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('81c8abf1-8b97-4908-abd8-06695335e8f0', '20200327020134', 'anonymousUser', '2020-03-27 14:01:34.133', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('2feaa6ec-ca3c-4878-b07c-32a006f08e5c', '20200327020642', 'anonymousUser', '2020-03-27 14:06:42.18', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('02f0c542-de1e-4b92-8579-d9303daa6b5f', '20200327020218', 'anonymousUser', '2020-03-27 14:02:18.285', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b0b0595e-6fc4-4b84-a0b1-2722ca6b296d', '20200327020224', 'anonymousUser', '2020-03-27 14:02:24.574', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('45b8b049-fb35-4dfb-9b22-a73bf0dfa2fc', '20200327020227', 'anonymousUser', '2020-03-27 14:02:27.077', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('61a7bc0f-35d0-4656-998a-50f1edd66168', '20200327020326', 'anonymousUser', '2020-03-27 14:03:26.115', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('22086d19-0857-4873-94b2-ba75a3fc54fc', '20200327020329', 'anonymousUser', '2020-03-27 14:03:29.648', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('1ff544a0-5f85-419c-8057-65651ea05088', '20200327020335', 'anonymousUser', '2020-03-27 14:03:35.86', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('31c4e87b-8ed7-4c55-b1ef-7f6e82e24dd7', '20200327020922', 'anonymousUser', '2020-03-27 14:09:22.43', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('b6919f1e-ce78-4218-8486-0ef3776e9344', '20200327020925', 'anonymousUser', '2020-03-27 14:09:25.923', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('fe6bdb88-6532-4372-9870-1067240382e5', '20200327020931', 'anonymousUser', '2020-03-27 14:09:31.961', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('8ac062a8-169c-4dcc-818d-0149fbc0fdb0', '20200327020938', 'root', '2020-03-27 14:09:38.697', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('655a41f0-094a-4f86-b828-5f6fa3a18977', '20200327020938', 'root', '2020-03-27 14:09:38.997', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('7d1d9f20-d0ba-4a1c-91d9-6b56f49678cc', '20200327020948', 'anonymousUser', '2020-03-27 14:09:48.145', '获取office配置', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('56d2119c-423b-4737-88fd-3e0a4ba196fa', '20200327020952', 'anonymousUser', '2020-03-27 14:09:52.972', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('bfaf973a-5e9d-4c31-a048-e95053bca545', '20200327020954', 'anonymousUser', '2020-03-27 14:09:54.118', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('586624a8-7e65-449e-ac98-29a25b01118b', '20200327021156', 'anonymousUser', '2020-03-27 14:11:56.346', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('9865f0c0-aca8-45bf-aeab-61e498efb208', '20200327020714', 'root', '2020-03-27 14:07:14.517', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('40c8fb9a-376b-4e95-8617-1d3813814620', '20200327021002', 'root', '2020-03-27 14:10:02.35', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8b6243d9-eacc-4641-a9ea-abc49c1a8c35', '20200327021028', 'root', '2020-03-27 14:10:28.244', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('0574ab05-9074-4894-b5a4-0f8bdee37c9d', '20200327021106', 'root', '2020-03-27 14:11:06.365', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('00d679c5-bc2f-43c6-8025-e5d9d85f3e1b', '20200327021121', 'root', '2020-03-27 14:11:21.558', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('63c2afab-b5d4-4a5b-94d6-043c735e2610', '20200327021149', 'root', '2020-03-27 14:11:49.448', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('de5cac93-f1ec-418d-a0f6-5cf497d73f46', '20200327021217', 'root', '2020-03-27 14:12:17.745', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('5f0431df-3538-4a21-a32e-2cf5fdf8b798', '20200327021235', 'root', '2020-03-27 14:12:35.032', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('d1aca17d-d593-49e2-8a09-63db199c17a7', '20200327021257', 'root', '2020-03-27 14:12:57.466', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('acfb1af5-df58-496d-9010-5694a5889e79', '20200327021312', 'root', '2020-03-27 14:13:12.551', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('65de179e-cb48-4554-b299-a33b64c3013c', '20200327021323', 'root', '2020-03-27 14:13:23.125', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6beae47c-9a4f-4992-8403-13e3ed1d11b7', '20200327021325', 'root', '2020-03-27 14:13:25.029', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('cab48d75-7688-4567-bbc3-99b7336e1023', '20200327022019', 'root', '2020-03-27 14:20:19.646', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('3eb310e6-381e-4180-bdab-bd03445d85c8', '20200327022022', 'root', '2020-03-27 14:20:22.217', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9fea6385-fd61-470e-9d16-27ed3e4ee78a', '20200327022024', 'root', '2020-03-27 14:20:24.094', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c6d5c7de-cfbe-4fff-9903-2d083d883a61', '20200327022025', 'root', '2020-03-27 14:20:25.103', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('415109f0-53a3-4cea-90a5-c54ea72aa8ea', '20200327022031', 'root', '2020-03-27 14:20:31.254', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('df38b695-7e2d-4cd6-830c-f279e24dc9df', '20200327022413', 'root', '2020-03-27 14:24:13.76', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('265e2091-bb6e-4d0a-ae3e-83e53d87bfe4', '20200327022501', 'root', '2020-03-27 14:25:01.262', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ecfc1cd5-59bd-4e83-891f-0d5f705886fd', '20200327022533', 'root', '2020-03-27 14:25:33.452', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('295cc1b3-5351-4343-bb46-d9d1c4b31fb3', '20200327022255', 'anonymousUser', '2020-03-27 14:22:55.962', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('036e444b-05f4-491d-875b-ceb203df51d8', '20200327022744', 'root', '2020-03-27 14:27:44.88', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6fea625a-7d48-48e1-90cd-a09638a658c1', '20200327022746', 'root', '2020-03-27 14:27:46.308', '文档管理单个查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('58cd0e0d-f939-4c25-83c8-45d23730515a', '20200327022746', 'root', '2020-03-27 14:27:46.462', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('63b466d2-4340-4484-8a2c-166bc0387cf3', '20200327022919', 'root', '2020-03-27 14:29:19.279', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('663de1dd-99d9-403b-856c-613a27735346', '20200327022919', 'root', '2020-03-27 14:29:19.564', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('f05a7155-4faa-432e-a884-bf9b749dfdaa', '20200327023158', 'root', '2020-03-27 14:31:58.461', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('910ecddc-0510-433f-b8e6-b8ddc6af24b0', '20200327023233', 'root', '2020-03-27 14:32:33.863', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a71b85d0-e373-4a9c-bc9b-72acb198e23b', '20200327023418', 'root', '2020-03-27 14:34:18.153', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('12303d16-d933-445b-bb99-564798871f93', '20200327023515', 'root', '2020-03-27 14:35:15.387', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6db83492-28da-414f-8ba7-494ef92df0c1', '20200327023625', 'root', '2020-03-27 14:36:25.145', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('331909e2-9e46-43c8-8edf-07e755dffd73', '20200327023350', 'root', '2020-03-27 14:33:50.264', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('03d5a1b8-b99e-4204-a358-10804ebd5cb2', '20200327023350', 'root', '2020-03-27 14:33:50.473', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('12136d77-8fdd-4867-9cfd-169b149fdc64', '20200327023629', 'root', '2020-03-27 14:36:29.367', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b23e9b5f-49e8-40bc-bf8e-ca696c24fb5b', '20200327023637', 'anonymousUser', '2020-03-27 14:36:37.039', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('7ece5209-a432-4551-a0c7-b011e0c0d687', '20200327023649', 'root', '2020-03-27 14:36:49.484', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('76eae49c-a763-4553-aac5-116ef82ea807', '20200327023625', 'anonymousUser', '2020-03-27 14:36:25.687', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('778df6b8-1289-4420-a3b4-19b70d0f3ba6', '20200327023653', 'anonymousUser', '2020-03-27 14:36:53.647', '编辑文档', NULL, '116.62.45.61');
INSERT INTO "public"."sys_log" VALUES ('fb6a5e95-9b43-436b-8344-0c319b6c0477', '20200327023945', 'root', '2020-03-27 14:39:45.164', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('d5c463a1-1c76-4d90-9241-90e051c1b8ec', '20200327024037', 'root', '2020-03-27 14:40:37.572', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('95683995-4d69-4f9f-8d4a-6bba3e2f6886', '20200327024053', 'root', '2020-03-27 14:40:53.012', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a3b66d3a-a8a3-4bbc-a1e1-1586e264dd74', '20200327024108', 'root', '2020-03-27 14:41:08.488', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('999c5f52-0a2b-4fe2-b29d-52c48ffc17c3', '20200327024112', 'root', '2020-03-27 14:41:12.542', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('bb0d86e5-0846-4ff9-b7cc-545388efc653', '20200327024114', 'root', '2020-03-27 14:41:14.147', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('51e79d05-399c-4c4d-af32-bcfc9f2ede7c', '20200327023847', 'anonymousUser', '2020-03-27 14:38:47.598', '获取office配置', NULL, '127.0.0.1');
INSERT INTO "public"."sys_log" VALUES ('30fa3b43-371b-4b7a-9380-d7f41012315f', '20200327023901', 'anonymousUser', '2020-03-27 14:39:01.212', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('809f7259-80e8-4506-b751-36b68d43268a', '20200327023944', 'anonymousUser', '2020-03-27 14:39:44.594', '获取office配置', NULL, '127.0.0.1');
INSERT INTO "public"."sys_log" VALUES ('db531d5f-4a0f-4294-821f-15cad4a748cb', '20200327023950', 'anonymousUser', '2020-03-27 14:39:50.571', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('c6936a0e-6899-4204-b8a4-e3eb32f629ef', '20200327024003', 'anonymousUser', '2020-03-27 14:40:03.721', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('49a0f23e-97c3-46f6-9708-417fdfd81e1f', '20200327024403', 'root', '2020-03-27 14:44:03.174', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('b424c8ac-2346-4eaf-be8a-60a90693d69c', '20200327024443', 'root', '2020-03-27 14:44:43.595', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('3b9435a9-ca42-4359-8268-f92778976d21', '20200327024451', 'root', '2020-03-27 14:44:51.204', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('2d168865-41d6-4b13-b52d-5efd1046d044', '20200327024525', 'root', '2020-03-27 14:45:25.608', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f083792d-0f78-4495-9e6e-3b63cbe46186', '20200327024652', 'root', '2020-03-27 14:46:52.512', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('23674ea3-85e9-4b5e-b285-c15b06ab1c45', '20200327024417', 'anonymousUser', '2020-03-27 14:44:17.943', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('29d40fad-69f8-4e7d-a2df-11c0b954ca7a', '20200327024422', 'anonymousUser', '2020-03-27 14:44:22.346', '获取office配置', NULL, '127.0.0.1');
INSERT INTO "public"."sys_log" VALUES ('ea2b8a77-1306-46d0-aa0f-6c796bdd7628', '20200327024428', 'anonymousUser', '2020-03-27 14:44:28.277', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('6dfade4d-63f6-4e1d-835c-23712547d088', '20200327024448', 'anonymousUser', '2020-03-27 14:44:48.589', '编辑文档', NULL, '192.168.0.129');
INSERT INTO "public"."sys_log" VALUES ('4c22aedb-1dcb-4671-9982-1fb19e968dc7', '20200327024520', 'anonymousUser', '2020-03-27 14:45:20.786', '编辑文档', NULL, '192.168.0.129');
INSERT INTO "public"."sys_log" VALUES ('ff8839b2-f29a-48f7-a1cf-a1df502617ed', '20200327024633', 'anonymousUser', '2020-03-27 14:46:33.805', '编辑文档', NULL, '192.168.0.129');
INSERT INTO "public"."sys_log" VALUES ('981e835d-53ff-4605-a6cd-0d4c52c2b21d', '20200327025755', 'anonymousUser', '2020-03-27 14:57:55.765', '获取office配置', NULL, '127.0.0.1');
INSERT INTO "public"."sys_log" VALUES ('830ec949-c67e-4564-9e58-b7f904abde76', '20200327025809', 'anonymousUser', '2020-03-27 14:58:09.828', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('1609db8f-e45f-4588-9596-a891c70bea6d', '20200327030851', 'anonymousUser', '2020-03-27 15:08:51.309', '编辑文档', NULL, '192.168.0.129');
INSERT INTO "public"."sys_log" VALUES ('bfffbd88-ec7c-4a34-9680-f749441fc527', '20200327025059', 'root', '2020-03-27 14:50:59.661', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8458b3fa-e8b0-4b64-a095-28fe39990212', '20200327025145', 'root', '2020-03-27 14:51:45.107', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6346d78c-d86a-4712-b082-3a7125079710', '20200327025309', 'root', '2020-03-27 14:53:09.52', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('3747ccfe-1c0a-4f43-b5c9-2e04be53d5c1', '20200327025319', 'root', '2020-03-27 14:53:19.626', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('29f0688d-efa9-4018-8c97-675d1ba2053e', '20200327025346', 'root', '2020-03-27 14:53:46.926', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('cf3028fb-2fac-41b8-b403-bf33406569eb', '20200327025119', 'root', '2020-03-27 14:51:19.826', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('200ee13e-6aae-40fc-a7e0-1e3ea652268e', '20200327025120', 'root', '2020-03-27 14:51:20.065', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('1fa10493-4a4c-4448-a765-11985b252357', '20200327025358', 'root', '2020-03-27 14:53:58.938', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a8329760-004b-4f72-9d28-ad0886888c4b', '20200327025401', 'root', '2020-03-27 14:54:01.567', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a117961c-7abf-474e-b95d-7fb01385d514', '20200327025452', 'root', '2020-03-27 14:54:52.265', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('04ac860a-0e32-493d-8fd4-f245d698b1a8', '20200327025456', 'root', '2020-03-27 14:54:56.644', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('99ff1744-1c67-410d-881c-232e8e5b0002', '20200327025523', 'root', '2020-03-27 14:55:23.407', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ba52aef5-eb12-464d-acb9-da09f67aa60b', '20200327025528', 'root', '2020-03-27 14:55:28.83', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8ddcd741-a760-44af-b7cf-53ffcc398ed8', '20200327025540', 'root', '2020-03-27 14:55:40.419', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('fbd24a05-5d84-4d3e-a10e-8e6ae3198d77', '20200327025556', 'root', '2020-03-27 14:55:56.257', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a585b7cf-b517-466f-959b-a48c18d5024f', '20200327025559', 'root', '2020-03-27 14:55:59.114', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('096b2066-2b21-4cf5-a84c-0e6eb8e572e7', '20200327025602', 'root', '2020-03-27 14:56:02.96', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('297e425e-896b-4c65-9911-70bd034fc289', '20200327025604', 'root', '2020-03-27 14:56:04.583', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('bcbdd36a-1a72-4999-8725-619361e3504e', '20200327025605', 'root', '2020-03-27 14:56:05.779', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('5cb29a0e-f770-4894-9753-e6219f8dccad', '20200327025606', 'root', '2020-03-27 14:56:06.419', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ba75409e-3d87-41dc-bd5c-b31c860d574e', '20200327025606', 'root', '2020-03-27 14:56:06.931', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('0ba3bf7b-d9bf-4973-904b-02f4f2e15de1', '20200327025607', 'root', '2020-03-27 14:56:07.43', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('a6942172-bae5-4e29-9271-db7cdc514046', '20200327025607', 'root', '2020-03-27 14:56:07.909', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('183e40f9-16f1-4005-9496-7e646406d047', '20200327025911', 'root', '2020-03-27 14:59:11.593', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4becd2c2-851e-418d-9d69-973f5b2b2c1a', '20200327025929', 'root', '2020-03-27 14:59:29.352', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('55be87f9-3e31-48b7-9a7d-ab1743f4bdc0', '20200327030027', 'root', '2020-03-27 15:00:27.108', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f11aa8ad-e24b-4d8b-a8d0-fca723b092ae', '20200327030259', 'root', '2020-03-27 15:02:59.901', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e6f55a63-dea7-4162-9707-274b6982ec28', '20200327030505', 'root', '2020-03-27 15:05:05.043', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('bff0dcdb-4c61-4936-a9a9-f84edbdd2fe7', '20200327030437', 'root', '2020-03-27 15:04:37.054', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('37f54f7c-7e59-440c-ad9d-b83a274c0c2f', '20200327030437', 'root', '2020-03-27 15:04:37.468', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('d146c011-82f1-44a1-ab7e-63ed0adfaaeb', '20200327030716', 'root', '2020-03-27 15:07:16.381', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('eea9a086-5846-4b26-aee8-bf946100ddaf', '20200327030745', 'root', '2020-03-27 15:07:45.175', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('11cc0916-25b8-443e-983a-493a1c917836', '20200327030556', 'root', '2020-03-27 15:05:56.931', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('4a0b76bc-9d2f-4c82-bf5f-5a5a36043f5e', '20200327030557', 'root', '2020-03-27 15:05:57.178', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('0df9b6f7-e73a-40d5-9e4e-adfce04b742a', '20200327030836', 'root', '2020-03-27 15:08:36.122', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('0fea0d89-839f-4d51-aef6-b866aae4f104', '20200327030839', 'root', '2020-03-27 15:08:39.519', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8bf25370-272f-42aa-8a47-6725545a013a', '20200327030842', 'root', '2020-03-27 15:08:42.113', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1053f063-8851-4bd0-850a-b316fcc46805', '20200327030848', 'root', '2020-03-27 15:08:48.113', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('9e9c573c-0fd8-4cd3-9c8c-8bcaf12f50ae', '20200327031001', 'root', '2020-03-27 15:10:01.782', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('81bc2995-db44-4e76-863f-33e279c42f58', '20200327031155', 'root', '2020-03-27 15:11:55.05', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1b1f04aa-2f25-4c08-8e0f-053e39f85e3f', '20200327032258', 'anonymousUser', '2020-03-27 15:22:58.676', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('360a9448-ca79-4898-9e7f-c296e203ecb1', '20200327032143', 'anonymousUser', '2020-03-27 15:21:43.808', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('f5d78b58-603f-4e12-a002-63f8e999c30d', '20200327032424', 'root', '2020-03-27 15:24:24.25', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('65caec9b-b594-40ab-a5a9-fbbfc8afd3fe', '20200327032555', 'anonymousUser', '2020-03-27 15:25:55.893', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('d34da77b-f4ee-4a17-b5b8-8bb36a6f070f', '20200327032408', 'root', '2020-03-27 15:24:08.489', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('ec323902-cd03-49b4-bfa3-e6c0c125a95c', '20200327032408', 'root', '2020-03-27 15:24:08.518', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('7262897c-fc17-41a3-b2a8-9617856d9c07', '20200327032748', 'root', '2020-03-27 15:27:48.294', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ba8d298c-ffab-4c16-ad5e-9bce89a196a7', '20200327032814', 'anonymousUser', '2020-03-27 15:28:14.135', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('0ef3261f-0efa-49e8-ab35-2162aad2cd28', '20200327032908', 'anonymousUser', '2020-03-27 15:29:08.556', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('b728ce7c-60aa-4e17-ba80-ad38f862ae89', '20200327032920', 'anonymousUser', '2020-03-27 15:29:20.387', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('a9e83e51-3080-4362-b678-9e640e568364', '20200327033047', 'root', '2020-03-27 15:30:47.492', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('dce37a6e-69f6-49db-a00e-706f07d02b34', '20200327033047', 'root', '2020-03-27 15:30:47.772', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('fa6e9b9a-48cc-483b-b953-9abb8236f616', '20200327033048', 'root', '2020-03-27 15:30:48.699', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('e0b2b5a7-0ef5-49f3-9902-d8bc33603ee1', '20200327033049', 'root', '2020-03-27 15:30:49.018', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('c0b443d3-cdf9-46a0-a87a-80f4232b4552', '20200327033049', 'root', '2020-03-27 15:30:49.202', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b79e02f3-8608-4ec8-b3f9-0750d1a97732', '20200327033051', 'root', '2020-03-27 15:30:51.902', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('c57f3819-50be-4c34-9205-eae8a7b5fc3c', '20200327033547', 'root', '2020-03-27 15:35:47.287', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b9eec80c-1af3-44ab-a77d-f6c338f9f2eb', '20200327033552', 'root', '2020-03-27 15:35:52.674', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('507ee3d1-a58d-4dfd-b1c7-4f5246b68c09', '20200327034943', 'anonymousUser', '2020-03-27 15:49:43.768', '获取office配置', NULL, '127.0.0.1');
INSERT INTO "public"."sys_log" VALUES ('250af54e-1c01-4e9a-a34e-6b2efb545b4d', '20200327035001', 'anonymousUser', '2020-03-27 15:50:01.014', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('fceb9d8c-7ffd-4025-bfd6-bc4bbc661006', '20200327035517', 'anonymousUser', '2020-03-27 15:55:17.681', '获取office配置', NULL, '127.0.0.1');
INSERT INTO "public"."sys_log" VALUES ('dcf3bedd-986e-4ed1-955b-f008cc7626d6', '20200327035522', 'anonymousUser', '2020-03-27 15:55:22.62', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('12f6f52c-481b-4801-a257-3ffc1ba55437', '20200327035527', 'anonymousUser', '2020-03-27 15:55:27.375', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('4eb3df66-e165-44fc-8d26-9d0f25dcdb19', '20200327040205', 'anonymousUser', '2020-03-27 16:02:05.486', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('34b74cd1-3c69-4644-9d41-9720324ce315', '20200327040205', 'anonymousUser', '2020-03-27 16:02:05.726', '文档管理分页', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('5ef18997-2a03-4c83-a9df-4a0aed6eb3a2', '20200327040111', 'root', '2020-03-27 16:01:11.851', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b300c917-257b-42ec-af30-faa3fd1e53ac', '20200327040820', 'anonymousUser', '2020-03-27 16:08:20.117', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('469dc1b4-2363-4b7e-a5a2-dc59ca6003bf', '20200327041324', 'root', '2020-03-27 16:13:24.324', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('e0388820-64dc-4388-a6d5-6b8e5774a15d', '20200327041324', 'root', '2020-03-27 16:13:24.658', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('5d68431b-b6ca-4c89-abed-5cf5f8a9ad37', '20200327041324', 'root', '2020-03-27 16:13:24.703', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('709a3f78-e311-4574-b056-ef3cbd5b1754', '20200327041350', 'root', '2020-03-27 16:13:50.819', '修改文档管理', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('04d60802-7ed8-483a-868f-ab33199f48c2', '20200327041355', 'root', '2020-03-27 16:13:55.732', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('eb4ac711-851f-4d04-8b4e-ca89136d7db5', '20200327041442', 'root', '2020-03-27 16:14:42.556', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('259d7598-97dc-49cc-8876-8a6dd53ffc0d', '20200327041453', 'root', '2020-03-27 16:14:53.703', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('ebb71ad5-42a8-45bf-adaf-9b4086a9d20e', '20200327041457', 'root', '2020-03-27 16:14:57.674', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('51e4bf57-21b0-486b-920a-3660c4008719', '20200327041518', 'anonymousUser', '2020-03-27 16:15:18.143', '编辑文档', NULL, '192.168.0.112');
INSERT INTO "public"."sys_log" VALUES ('a07355cd-1c0f-441b-8cef-b2104df74a72', '20200327041603', 'root', '2020-03-27 16:16:03.286', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('ff4bf5e6-1541-46d4-b8af-e342549646ab', '20200327041626', 'root', '2020-03-27 16:16:26.858', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('999976e8-53cf-43a8-9dfd-3b40df45d2ab', '20200327041635', 'root', '2020-03-27 16:16:35.3', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('dadadea3-d04c-4d80-9ccd-550dcf852192', '20200327041806', 'root', '2020-03-27 16:18:06.246', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('3cba44df-2457-48a7-96ff-5a773ac810e1', '20200327041808', 'root', '2020-03-27 16:18:08.887', '文档管理所有查询', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('f78b3ffb-ebad-473b-8385-2a573dee0f86', '20200327041822', 'root', '2020-03-27 16:18:22.479', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('46a7a6af-2a45-4427-af26-e1a8467fd1c1', '20200327041822', 'root', '2020-03-27 16:18:22.793', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('d915a128-2021-43d2-9d04-32cf31402be6', '20200327042102', 'root', '2020-03-27 16:21:02.084', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('32ee7f24-c5d7-4a91-8c43-5ab1b2276139', '20200327041830', 'root', '2020-03-27 16:18:30.511', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('0a2c628e-8043-4340-9a05-87604c2a58b2', '20200327041830', 'root', '2020-03-27 16:18:30.814', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('3054b6ba-7364-44fd-9e4f-e748d13ba6cd', '20200327042109', 'root', '2020-03-27 16:21:09.725', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4ab8d20c-35be-44a5-9cc3-ecb9ae0a9161', '20200327042114', 'root', '2020-03-27 16:21:14.11', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('15b59070-1b12-488b-9de6-fa845802fc4e', '20200327042114', 'root', '2020-03-27 16:21:14.906', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('2ecabcf8-8444-4579-a938-7302c5a684d8', '20200327041938', 'anonymousUser', '2020-03-27 16:19:38.467', '用户登录', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('39af12c0-3a03-49bf-9da5-dcb7c6240458', '20200327041948', 'root', '2020-03-27 16:19:48.906', '用户登录', NULL, '183.67.56.46');
INSERT INTO "public"."sys_log" VALUES ('21033832-e18c-4dcc-974a-7c51525f5faf', '20200327042231', 'root', '2020-03-27 16:22:31.221', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8c3c0210-83b6-417c-85ad-e1d8f26ba48d', '20200327042237', 'root', '2020-03-27 16:22:37.569', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4afc9ea7-e7a9-4e90-9289-10410f58d434', '20200327042238', 'root', '2020-03-27 16:22:38.934', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('1eba2f24-eadf-48f8-9ef4-a00701c323d0', '20200327042306', 'root', '2020-03-27 16:23:06.107', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('8467911f-5e13-44dd-abac-1c0df973a4bc', '20200327042256', 'root', '2020-03-27 16:22:56.475', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('a47d79cc-6ac4-4154-9d14-96b2109d5839', '20200327042256', 'root', '2020-03-27 16:22:56.795', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('5683faa7-bff6-4028-bb48-976ff63baabe', '20200327042545', 'root', '2020-03-27 16:25:45.003', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('c8e921cd-9966-4316-b0f8-256cad7717b5', '20200327042959', 'anonymousUser', '2020-03-27 16:29:59.24', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('a476e5bb-303b-4e71-a879-3e00b38325c0', '20200327043114', 'anonymousUser', '2020-03-27 16:31:14.133', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('6271325c-c830-44d5-a29f-4d93766673f4', '20200327042848', 'root', '2020-03-27 16:28:48.947', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('73d1f9d1-9fb3-4195-ac62-d21c145dd8d7', '20200327042848', 'root', '2020-03-27 16:28:48.981', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('476af093-b43b-4bcd-b9db-e72013dc9a1a', '20200327043127', 'root', '2020-03-27 16:31:27.979', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ce44df52-931a-4ae9-8f91-56d51ee4013e', '20200327042959', 'root', '2020-03-27 16:29:59.604', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('d4631934-7e9d-4641-bfdd-cdddd8ab4283', '20200327043243', 'anonymousUser', '2020-03-27 16:32:43.361', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('290356f5-6af5-473f-93c5-54ff87832ec1', '20200327043321', 'anonymousUser', '2020-03-27 16:33:21.115', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('ecb55697-e691-499e-ba7d-c151b7fd9c12', '20200327043348', 'root', '2020-03-27 16:33:48.351', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6d74bc32-a1ff-437d-b751-f81f52719505', '20200327043349', 'root', '2020-03-27 16:33:49.721', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('6082eda6-4846-4afd-98dd-f8411ef94326', '20200327043530', 'root', '2020-03-27 16:35:30.459', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f6209913-0664-4fc2-8f43-3be5c9883ad2', '20200327043256', 'root', '2020-03-27 16:32:56.036', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('962fbea0-7b7d-4008-b5d4-cf19342b0316', '20200327043256', 'root', '2020-03-27 16:32:56.299', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('730e502e-a3e2-4f8e-86b6-213c0344a8b4', '20200327043535', 'root', '2020-03-27 16:35:35.197', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('317470da-ad4d-4510-82ca-312cb1fd00f9', '20200327043539', 'root', '2020-03-27 16:35:39.275', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('61339726-ea39-4438-aea9-5736e9ed7346', '20200327043540', 'root', '2020-03-27 16:35:40.377', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('73e9423f-4e6a-462d-80e3-0ece14eef7b4', '20200327043638', 'root', '2020-03-27 16:36:38.729', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ae154b6f-85e6-4ac7-b339-493b01ea6fdb', '20200327043702', 'root', '2020-03-27 16:37:02.358', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('ca0a6fe5-7969-45b8-ba16-c4f006a5660b', '20200327043703', 'root', '2020-03-27 16:37:03.087', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('bf322f51-d271-4c7b-bed7-852e9910fc04', '20200327043940', 'anonymousUser', '2020-03-27 16:39:40.115', '获取选择的所有文件', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('1d1aec04-0b44-4704-bc5a-fcec5e33ac17', '20200327044042', 'root', '2020-03-27 16:40:42.067', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('47eea7ad-f684-4d4d-aab6-038e8207c1b3', '20200327044315', 'root', '2020-03-27 16:43:15.717', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('bf96f90a-d407-42c5-b4e8-e8343be31631', '20200327044036', 'root', '2020-03-27 16:40:36.527', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('c8ad2c5c-111d-4281-9ffa-96bd6a355135', '20200327044036', 'root', '2020-03-27 16:40:36.801', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b0f4535b-50b2-46de-89b4-38cac0a08867', '20200327044448', 'root', '2020-03-27 16:44:48.762', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('4b76825c-e2ab-42c7-aaff-58f08843fcae', '20200327044604', 'root', '2020-03-27 16:46:04.475', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('008e1bd3-60dc-4a3f-9630-d1b632c22839', '20200327044709', 'anonymousUser', '2020-03-27 16:47:09.572', '修改多个文档管理', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('be3b4478-ec58-4823-a8cd-f5b5a61782ed', '20200327044726', 'root', '2020-03-27 16:47:26.696', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('90385020-63c6-4a0c-8d4f-1067bf3bba60', '20200327044736', 'anonymousUser', '2020-03-27 16:47:36.248', '修改多个文档管理', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('583599ac-9016-4f2a-a80f-af72a59b2350', '20200327044815', 'root', '2020-03-27 16:48:15.362', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('fe029401-efeb-44ea-bdbd-b4b1062596ac', '20200327044832', 'root', '2020-03-27 16:48:32.889', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('f8e1b2ce-e38d-48a4-9193-3ac886cd80f0', '20200327044834', 'root', '2020-03-27 16:48:34.087', '文档管理所有查询', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('e4b90d5c-cc56-45d8-9343-79635640b0e1', '20200327044834', 'root', '2020-03-27 16:48:34.955', '修改多个文档管理', NULL, '192.168.1.52');
INSERT INTO "public"."sys_log" VALUES ('994702d1-7232-46b7-aeeb-88dbe660e666', '20200327044903', 'anonymousUser', '2020-03-27 16:49:03.889', '修改多个文档管理', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO "public"."sys_log" VALUES ('d40f48e2-3189-4775-a662-9aafeef2b2da', '20200327044952', 'root', '2020-03-27 16:49:52.533', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('abf68874-1957-44b0-83e7-4d0df93f82db', '20200327044952', 'root', '2020-03-27 16:49:52.763', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('81e6e985-6d11-447d-914d-3eecae39e5aa', '20200327045235', 'root', '2020-03-27 16:52:35.867', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('5d29817e-7171-44d2-851f-0588b930b4db', '20200327045236', 'root', '2020-03-27 16:52:36.116', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b62d5807-30a9-4842-85a1-e53f7a881f24', '20200327045304', 'root', '2020-03-27 16:53:04.883', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('97f1ddfa-aeae-4e3d-96b2-4bc8acf62d5c', '20200327045305', 'root', '2020-03-27 16:53:05.14', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('9015793a-7715-4722-8b15-3b42981e29f7', '20200327045337', 'root', '2020-03-27 16:53:37.916', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('f28d565f-9451-4fbd-a769-c9222c3b109a', '20200327045338', 'root', '2020-03-27 16:53:38.258', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('4ee9c453-eff3-407f-a5b7-90b217c6b59b', '20200327045346', 'root', '2020-03-27 16:53:46.704', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('bd040b28-1e2a-4536-bd96-4d4a26b577e2', '20200327045347', 'root', '2020-03-27 16:53:47.005', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('0f2fc115-1792-4014-9664-08770c9e6399', '20200327045445', 'root', '2020-03-27 16:54:45.308', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b079fbeb-e0e5-49d9-838a-14c81573b0da', '20200327045445', 'root', '2020-03-27 16:54:45.622', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('acc7eb67-f4a4-4842-b5fb-ccdae6e8d102', '20200327045755', 'root', '2020-03-27 16:57:55.489', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('99a24189-6790-4241-9ce9-109d52c50948', '20200327045755', 'root', '2020-03-27 16:57:55.751', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b2f8d829-39d7-4f90-a7c0-eb8a020ca182', '20200327045914', 'root', '2020-03-27 16:59:14.341', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('3f0d40a7-e936-48df-996c-b7272675e58c', '20200327050708', 'root', '2020-03-27 17:07:08.372', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('54e5252d-5b1b-4484-be59-65e93fee8dba', '20200327052030', 'root', '2020-03-27 17:20:30.614', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('f3b770e8-260d-4865-8ada-1a50181749d0', '20200327052030', 'root', '2020-03-27 17:20:30.869', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('1f6e12e4-cba9-46b2-b2b7-37bcc39f7346', '20200327052617', 'root', '2020-03-27 17:26:17.624', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('f66c7095-5327-4ad4-834b-23bbcd26276a', '20200327052617', 'root', '2020-03-27 17:26:17.87', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('bca9fc3e-4527-4a5b-b0db-52944d6e290f', '20200327052845', 'root', '2020-03-27 17:28:45.984', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('fe599970-59f5-4ecf-9351-2a259e7aba68', '20200327053111', 'root', '2020-03-27 17:31:11.618', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('870052ec-dd35-40f8-bfe3-7f93e035ace7', '20200327053211', 'root', '2020-03-27 17:32:11.873', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('2c4ed8ba-cdc7-410d-b953-45f5164b4efd', '20200327053537', 'root', '2020-03-27 17:35:37.629', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('0780cc26-ba76-4286-a155-1972006af6a0', '20200327053944', 'root', '2020-03-27 17:39:44.494', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('73bbe309-3795-4e45-87e4-36a4a464826d', '20200327053944', 'root', '2020-03-27 17:39:44.57', '获取分页角色', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('44189eaf-b349-430e-ae3d-a750538603c6', '20200327053947', 'root', '2020-03-27 17:39:47.319', '获取分页用户', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('c34b99ae-1eda-42fe-ab41-f0b280de869f', '20200327053947', 'root', '2020-03-27 17:39:47.332', '获取所有部门', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('8591db59-3330-4a37-9195-ee951ee790bf', '20200327054447', 'root', '2020-03-27 17:44:47.823', '获取分页角色', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('3ac31c8d-72a2-40f0-bb0c-8592802bacbb', '20200327054449', 'root', '2020-03-27 17:44:49.026', '获取所有部门', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('f556672c-360b-4b42-a59a-d5a3155e7482', '20200327054449', 'root', '2020-03-27 17:44:49.08', '获取分页用户', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b23ef23b-c554-49e4-a98c-bd74eb17f7a1', '20200327054525', 'root', '2020-03-27 17:45:25.216', '获取所有部门', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('18911509-5aa3-45e1-b4c8-28c269e8014c', '20200327054525', 'root', '2020-03-27 17:45:25.225', '获取分页用户', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('f7bf7dd7-f915-4c0d-be87-8e3ec24e65ed', '20200327054532', 'root', '2020-03-27 17:45:32.499', '获取所有部门', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('b7c4a4b1-b60b-4fd2-ad63-3e760a30df2d', '20200327054532', 'root', '2020-03-27 17:45:32.508', '获取分页用户', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('c22b3f58-b54b-4d5a-a6f6-244bdc2c028a', '20200327054537', 'root', '2020-03-27 17:45:37.954', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('7b134992-5ac8-4252-be22-7fb261c5c765', '20200327054546', 'root', '2020-03-27 17:45:46.477', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('56470bc6-a064-4146-bb1a-9650f1d330f1', '20200327054829', 'root', '2020-03-27 17:48:29.404', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('7b1953d1-baf8-454d-85ff-71bae737349c', '20200327054931', 'root', '2020-03-27 17:49:31.672', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('ac4b2b94-2383-4ee1-abb4-00d670c7a709', '20200327054953', 'root', '2020-03-27 17:49:53.349', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('ca0f965d-50d7-4782-9250-aafb33507c0f', '20200327054953', 'root', '2020-03-27 17:49:53.4', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('7435e35b-5a5e-467a-8bc7-20af4c2d2a33', '20200327055000', 'root', '2020-03-27 17:50:00.471', '文件上传', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('42f001e1-902b-4598-be81-3ab2ffa01ba6', '20200327055502', 'root', '2020-03-27 17:55:02.897', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('2481cbcb-26c0-490a-8b5f-578b7a2270af', '20200327055503', 'root', '2020-03-27 17:55:03.185', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('a09d1d79-5387-4181-a3d3-8117e8b1ab56', '20200327055553', 'root', '2020-03-27 17:55:53.356', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('e094d448-5116-4b08-8b09-64e981f11891', '20200327055553', 'root', '2020-03-27 17:55:53.675', '获取当前用户菜单树', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('be4b682c-4ca7-45c8-9b30-a3ad64c37ade', '20200327055722', 'root', '2020-03-27 17:57:22.313', '获取所有当前用户菜单', NULL, '183.67.57.48');
INSERT INTO "public"."sys_log" VALUES ('2597b654-c66f-40fb-891e-aa95c96cf82f', '20200327055722', 'root', '2020-03-27 17:57:22.583', '获取当前用户菜单树', NULL, '183.67.57.48');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_menu";
CREATE TABLE "public"."sys_menu" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "pid" varchar(32) COLLATE "pg_catalog"."default",
  "coder" varchar(30) COLLATE "pg_catalog"."default",
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "url" varchar(255) COLLATE "pg_catalog"."default",
  "icon_url" varchar(255) COLLATE "pg_catalog"."default",
  "reserve" varchar(255) COLLATE "pg_catalog"."default",
  "menu_group" varchar(30) COLLATE "pg_catalog"."default",
  "status" int2 DEFAULT 1
)
;
COMMENT ON COLUMN "public"."sys_menu"."id" IS '菜单id';
COMMENT ON COLUMN "public"."sys_menu"."name" IS '菜单名称';
COMMENT ON COLUMN "public"."sys_menu"."pid" IS '菜单父id';
COMMENT ON COLUMN "public"."sys_menu"."coder" IS '菜单编码';
COMMENT ON COLUMN "public"."sys_menu"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."sys_menu"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_menu"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."sys_menu"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."sys_menu"."url" IS '跳转地址';
COMMENT ON COLUMN "public"."sys_menu"."icon_url" IS '图标地址';
COMMENT ON COLUMN "public"."sys_menu"."reserve" IS '预留（备用）';
COMMENT ON COLUMN "public"."sys_menu"."menu_group" IS '菜单组';
COMMENT ON COLUMN "public"."sys_menu"."status" IS '菜单状态(0：禁用；1：启用)';
COMMENT ON TABLE "public"."sys_menu" IS '菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO "public"."sys_menu" VALUES ('82', '我的审批', '80', '82', 'root', '2020-03-06 11:14:50.7786', NULL, NULL, '/office/myProcess/approval', NULL, NULL, 'work', 1);
INSERT INTO "public"."sys_menu" VALUES ('72', '项目列表', '70', '72', 'root', '2020-03-06 11:12:12.1316', NULL, NULL, '/project/projectList', NULL, 'projectList', 'proj', 1);
INSERT INTO "public"."sys_menu" VALUES ('91', '配置列表', '90', '91', 'root', '2020-03-06 11:15:46.5596', NULL, NULL, '/office/processLayout/layoutList', NULL, NULL, 'work', 1);
INSERT INTO "public"."sys_menu" VALUES ('121', '员工统计', '120', '121', 'root', '2020-03-06 11:31:24.4136', NULL, NULL, '/statistic/staffStatistic', NULL, 'staffStatistic', 'stat', 1);
INSERT INTO "public"."sys_menu" VALUES ('21', '公司文档', '20', '21', 'root', '2020-03-06 10:28:31.0956', NULL, NULL, '/repository/documentMgt/company', NULL, NULL, 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('122', '测评考试统计', '120', '122', 'root', '2020-03-06 11:31:56.1306', NULL, NULL, '/statistic/testStatistic', NULL, 'testStatistic', 'stat', 1);
INSERT INTO "public"."sys_menu" VALUES ('41', '知识分类管理', '40', '41', 'root', '2020-03-06 10:58:39.5096', NULL, NULL, '/repository/warehouseMgt/classifyMgt', NULL, NULL, 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('42', '精品文档管理', '40', '42', 'root', '2020-03-06 10:59:30.5196', NULL, NULL, '/repository/warehouseMgt/qualityDocument', NULL, NULL, 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('43', '动态图管理', '40', '43', 'root', '2020-03-06 11:00:35.7036', NULL, NULL, '/repository/warehouseMgt/gif', NULL, NULL, 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('123', '知识仓库统计', '120', '123', 'root', '2020-03-06 11:32:28.4826', NULL, NULL, '/statistic/repositoryStatistic', NULL, 'repositoryStatistic', 'stat', 1);
INSERT INTO "public"."sys_menu" VALUES ('13', '角色管理', '10', '13', 'root', '2020-03-06 10:14:50.8096', NULL, NULL, '/system/systemSetup/roleMgt', NULL, 'roleMgt', 'sys', 1);
INSERT INTO "public"."sys_menu" VALUES ('14', '用户管理', '10', '14', 'root', '2020-03-06 10:17:54.6986', NULL, NULL, '/system/systemSetup/userMgt', NULL, 'userMgt', 'sys', 1);
INSERT INTO "public"."sys_menu" VALUES ('101', '学习中心', '100', '101', 'root', '2020-03-06 11:17:18.0956', NULL, NULL, '/assess/assessMgt/learningCenter', NULL, NULL, 'eval', 1);
INSERT INTO "public"."sys_menu" VALUES ('15', '组织管理', '10', '15', 'root', '2020-03-06 10:19:11.1046', NULL, NULL, '/system/systemSetup/organization', NULL, 'organization', 'sys', 1);
INSERT INTO "public"."sys_menu" VALUES ('102', '考试中心', '100', '102', 'root', '2020-03-06 11:17:50.2296', NULL, NULL, '/assess/assessMgt/testingCenter', NULL, NULL, 'eval', 1);
INSERT INTO "public"."sys_menu" VALUES ('111', '学习资源', '110', '111', 'root', '2020-03-06 11:28:20.8826', NULL, NULL, '/assess/resourceMgt/learningResource', NULL, NULL, 'eval', 1);
INSERT INTO "public"."sys_menu" VALUES ('112', '考试管理', '110', '112', 'root', '2020-03-06 11:28:49.0166', NULL, NULL, '/assess/resourceMgt/testMgt', NULL, NULL, 'eval', 1);
INSERT INTO "public"."sys_menu" VALUES ('113', '试卷库', '110', '113', 'root', '2020-03-06 11:29:24.4006', NULL, NULL, '/assess/resourceMgt/testPaperLibrary', NULL, NULL, 'eval', 1);
INSERT INTO "public"."sys_menu" VALUES ('114', '题库', '110', '114', 'root', '2020-03-06 11:29:49.1526', NULL, NULL, '/assess/resourceMgt/itemBank', NULL, NULL, 'eval', 1);
INSERT INTO "public"."sys_menu" VALUES ('81', '我发起的', '80', '81', 'root', '2020-03-06 11:14:21.0986', NULL, NULL, '/office/myProcess/created', NULL, NULL, 'work', 1);
INSERT INTO "public"."sys_menu" VALUES ('17', '操作日志', '10', '17', 'root', '2020-03-06 10:20:40.9556', NULL, NULL, '/system/systemSetup/operationLog', NULL, 'operationLog', 'sys', 1);
INSERT INTO "public"."sys_menu" VALUES ('71', '项目立项', '70', '71', 'root', '2020-03-06 11:11:44.1456', NULL, NULL, '/project/projectApply', NULL, 'projectApply', 'proj', 1);
INSERT INTO "public"."sys_menu" VALUES ('90', '流程配置', '500', '90', 'root', '2020-03-06 11:15:21.7576', NULL, NULL, '/office/processLayout', 'static/images/menu-icon-layout.png', NULL, 'work', 1);
INSERT INTO "public"."sys_menu" VALUES ('62', '话题管理', '60', '62', 'root', '2020-03-06 11:08:57.9246', NULL, NULL, '/community/communityMgt/topicMgt', NULL, 'community', 'community', 1);
INSERT INTO "public"."sys_menu" VALUES ('20', '文档管理', '300', '20', 'root', '2020-03-06 10:28:03.9366', NULL, NULL, '/repository/documentMgt', 'static/images/menu-icon-doc.png', 'repository', 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('400', '知识社区', '0', '400', 'root', '2020-03-28 16:14:25.8141', NULL, NULL, '/community', NULL, 'community', 'community', 1);
INSERT INTO "public"."sys_menu" VALUES ('73', '回收站', '70', '73', 'root', '2020-03-06 11:12:42.6666', NULL, NULL, '/project/recycleBin', NULL, 'project', 'proj', 1);
INSERT INTO "public"."sys_menu" VALUES ('50', '话题圈子类别', '400', '50', 'root', '2020-03-06 11:06:25.6836', NULL, NULL, '/community/topicCircle', 'static/images/menu-icon-topic.png', NULL, 'community', 1);
INSERT INTO "public"."sys_menu" VALUES ('110', '资源管理', '600', '110', 'root', '2020-03-06 11:26:52.5426', NULL, NULL, '/assess/resourceMgt', 'static/images/menu-icon-resource.png', NULL, 'eval', 1);
INSERT INTO "public"."sys_menu" VALUES ('100', '测评管理', '600', '100', 'root', '2020-03-06 11:16:41.4506', NULL, NULL, '/assess/assessMgt', 'static/images/menu-icon-assess.png', '', 'eval', 1);
INSERT INTO "public"."sys_menu" VALUES ('80', '我的流程', '500', '80', 'root', '2020-03-06 11:13:51.8846', NULL, NULL, '/office/myProcess', 'static/images/menu-icon-process.png', NULL, 'work', 1);
INSERT INTO "public"."sys_menu" VALUES ('22', '部门文档', '20', '22', 'root', '2020-03-06 10:30:43.0076', NULL, NULL, '/repository/documentMgt/departmental', NULL, NULL, 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('23', '个人文档', '20', '23', 'root', '2020-03-06 10:31:48.0856', NULL, NULL, '/repository/documentMgt/persona', NULL, NULL, 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('70', '项目管理', '900', '70', 'root', '2020-03-06 11:11:12.4966', NULL, NULL, '/project', 'static/images/menu-icon-project.png', 'project', 'proj', 1);
INSERT INTO "public"."sys_menu" VALUES ('500', '协同办公', '0', '500', 'root', '2020-03-26 16:14:45.8871', NULL, NULL, '/office', NULL, 'office', 'work', 1);
INSERT INTO "public"."sys_menu" VALUES ('120', '统计管理', '800', '120', 'root', '2020-03-06 11:30:50.8506', NULL, NULL, '/statistic', 'static/images/menu-icon-statistic.png', 'statistic', 'stat', 1);
INSERT INTO "public"."sys_menu" VALUES ('10', '系统管理', '200', '10', 'root', '2020-03-06 09:54:02.8896', NULL, NULL, '/system/systemSetup', 'static/images/menu-icon-sys.png', 'system', 'sys', 1);
INSERT INTO "public"."sys_menu" VALUES ('800', '统计分析', '0', '800', 'root', '2020-03-23 17:20:23.375', NULL, NULL, '/statistic', 'static/images/menu-icon-statistic.png', 'statistic', 'stat', 1);
INSERT INTO "public"."sys_menu" VALUES ('12', '菜单管理', '10', '11', 'root', '2020-03-06 10:14:15.1116', NULL, NULL, NULL, NULL, NULL, 'sys', 0);
INSERT INTO "public"."sys_menu" VALUES ('16', '字典管理', '10', '16', 'root', '2020-03-06 10:20:03.8996', NULL, NULL, NULL, NULL, NULL, 'sys', 0);
INSERT INTO "public"."sys_menu" VALUES ('40', '知识仓库管理', '300', '40', 'root', '2020-03-06 10:57:24.6956', NULL, NULL, '/repository/warehouseMgt', 'static/images/menu-icon-mgt.png', 'repository', 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('30', '知识分类', '300', '30', 'root', '2020-03-06 10:47:39.1536', NULL, NULL, '/repository/knowledgeClassify', 'static/images/menu-icon-sort.png', 'repository', 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('60', '知识社区管理', '400', '60', 'root', '2020-03-06 11:07:31.7146', NULL, NULL, '/community/communityMgt', 'static/images/menu-icon-community.png', 'community', 'community', 1);
INSERT INTO "public"."sys_menu" VALUES ('61', '圈子类别管理', '60', '61', 'root', '2020-03-06 11:08:16.3116', NULL, NULL, '/community/communityMgt/circleTypeMgt', NULL, 'community', 'community', 1);
INSERT INTO "public"."sys_menu" VALUES ('900', '项目协作', '0', '900', 'root', '2020-03-27 17:28:24.772', NULL, NULL, '/project', 'static/images/menu-icon-static/images/menu-icon-project.png', 'project', 'proj', 1);
INSERT INTO "public"."sys_menu" VALUES ('600', '知识测评', '0', '600', 'root', '2020-03-25 16:16:49.1696', NULL, NULL, '/assess', NULL, 'assess', 'eval', 1);
INSERT INTO "public"."sys_menu" VALUES ('220', '首页管理', '200', '220', 'root', '2020-03-17 10:26:04.844', NULL, NULL, '/system/homeMet', 'static/images/menu-icon-homeMgt.png', 'system', 'sys', 1);
INSERT INTO "public"."sys_menu" VALUES ('300', '知识仓库', '0', '300', 'root', '2020-03-29 16:10:47.3296', NULL, NULL, '/repository', NULL, 'repository', 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('230', '公司新闻', '220', '230', 'root', '2020-03-17 10:27:17.304', NULL, NULL, '/system/homeMgt/news', NULL, 'system', 'sys', 1);
INSERT INTO "public"."sys_menu" VALUES ('240', '通知公告', '220', '231', 'root', '2020-03-17 10:27:57.261', NULL, NULL, '/system/homeMgt/notice', NULL, 'system', 'sys', 1);
INSERT INTO "public"."sys_menu" VALUES ('200', '系统设置', '0', '200', 'root', '2020-03-24 17:31:01.628', NULL, NULL, '/system', 'static/images/menu-icon-sys.png', 'system', 'sys', 1);
INSERT INTO "public"."sys_menu" VALUES ('63', '我收藏的', '40', '63', 'root', '2020-03-06 11:09:36.7376', NULL, NULL, '/repository/warehouseMgt/myCollection', NULL, 'repositoryMyCollection', 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('44', '文档评论管理', '40', '44', 'root', '2020-03-06 11:02:22.5916', NULL, NULL, '/repository/warehouseMgt/documentComment', NULL, 'repositoryDocumentCommentMgt', 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('64', '我评论的', '40', '64', 'root', '2020-03-06 11:10:05.5956', NULL, NULL, '/repository/warehouseMgt/myComment', NULL, 'repositoryMyComment', 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('e34b8eb18de243808385baab6419c21f', '测试1', '30', NULL, 'root', '2020-03-30 14:34:38.116724', NULL, NULL, '/repository/knowledgeClassify/e34b8eb18de243808385baab6419c21f', NULL, 'repository', 'repo', 1);
INSERT INTO "public"."sys_menu" VALUES ('700', '首页', '0', '700', 'root', '2020-03-30 16:18:30.3216', NULL, NULL, '/home', NULL, NULL, 'home', 1);
INSERT INTO "public"."sys_menu" VALUES ('9add2f57b7a64163a0a063cfc9f6fcb7', '测试圈1', '50', NULL, 'root', '2020-04-03 10:12:32.523532', NULL, NULL, '/community/topicCircle/9add2f57b7a64163a0a063cfc9f6fcb7', NULL, 'community', 'community', 1);
INSERT INTO "public"."sys_menu" VALUES ('7c19976e01b5483a8c257c0fb3718b89', '测试圈2', '50', NULL, 'root', '2020-04-03 14:08:15.208446', NULL, NULL, '/community/topicCircle/7c19976e01b5483a8c257c0fb3718b89', NULL, 'community', 'community', 1);

-- ----------------------------
-- Table structure for sys_menu_map_power
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_menu_map_power";
CREATE TABLE "public"."sys_menu_map_power" (
  "menu_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "power_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."sys_menu_map_power"."menu_id" IS '菜单id';
COMMENT ON COLUMN "public"."sys_menu_map_power"."power_id" IS '权限id';
COMMENT ON TABLE "public"."sys_menu_map_power" IS '菜单权限表';

-- ----------------------------
-- Records of sys_menu_map_power
-- ----------------------------
INSERT INTO "public"."sys_menu_map_power" VALUES ('13', '30');
INSERT INTO "public"."sys_menu_map_power" VALUES ('13', '31');
INSERT INTO "public"."sys_menu_map_power" VALUES ('13', '32');
INSERT INTO "public"."sys_menu_map_power" VALUES ('13', '33');
INSERT INTO "public"."sys_menu_map_power" VALUES ('14', '40');
INSERT INTO "public"."sys_menu_map_power" VALUES ('14', '41');
INSERT INTO "public"."sys_menu_map_power" VALUES ('14', '42');
INSERT INTO "public"."sys_menu_map_power" VALUES ('14', '43');
INSERT INTO "public"."sys_menu_map_power" VALUES ('14', '44');
INSERT INTO "public"."sys_menu_map_power" VALUES ('15', '50');
INSERT INTO "public"."sys_menu_map_power" VALUES ('15', '51');
INSERT INTO "public"."sys_menu_map_power" VALUES ('15', '52');
INSERT INTO "public"."sys_menu_map_power" VALUES ('15', '53');
INSERT INTO "public"."sys_menu_map_power" VALUES ('15', '54');
INSERT INTO "public"."sys_menu_map_power" VALUES ('16', '60');
INSERT INTO "public"."sys_menu_map_power" VALUES ('16', '61');
INSERT INTO "public"."sys_menu_map_power" VALUES ('16', '62');
INSERT INTO "public"."sys_menu_map_power" VALUES ('16', '63');
INSERT INTO "public"."sys_menu_map_power" VALUES ('16', '64');
INSERT INTO "public"."sys_menu_map_power" VALUES ('17', '73');
INSERT INTO "public"."sys_menu_map_power" VALUES ('17', '74');
INSERT INTO "public"."sys_menu_map_power" VALUES ('21', '80');
INSERT INTO "public"."sys_menu_map_power" VALUES ('21', '81');
INSERT INTO "public"."sys_menu_map_power" VALUES ('21', '82');
INSERT INTO "public"."sys_menu_map_power" VALUES ('21', '83');
INSERT INTO "public"."sys_menu_map_power" VALUES ('21', '84');
INSERT INTO "public"."sys_menu_map_power" VALUES ('22', '90');
INSERT INTO "public"."sys_menu_map_power" VALUES ('22', '91');
INSERT INTO "public"."sys_menu_map_power" VALUES ('22', '92');
INSERT INTO "public"."sys_menu_map_power" VALUES ('22', '93');
INSERT INTO "public"."sys_menu_map_power" VALUES ('22', '94');
INSERT INTO "public"."sys_menu_map_power" VALUES ('22', '95');
INSERT INTO "public"."sys_menu_map_power" VALUES ('23', '100');
INSERT INTO "public"."sys_menu_map_power" VALUES ('23', '101');
INSERT INTO "public"."sys_menu_map_power" VALUES ('23', '102');
INSERT INTO "public"."sys_menu_map_power" VALUES ('23', '103');
INSERT INTO "public"."sys_menu_map_power" VALUES ('23', '104');
INSERT INTO "public"."sys_menu_map_power" VALUES ('23', '105');
INSERT INTO "public"."sys_menu_map_power" VALUES ('41', '110');
INSERT INTO "public"."sys_menu_map_power" VALUES ('41', '111');
INSERT INTO "public"."sys_menu_map_power" VALUES ('41', '112');
INSERT INTO "public"."sys_menu_map_power" VALUES ('41', '113');
INSERT INTO "public"."sys_menu_map_power" VALUES ('42', '120');
INSERT INTO "public"."sys_menu_map_power" VALUES ('42', '121');
INSERT INTO "public"."sys_menu_map_power" VALUES ('42', '122');
INSERT INTO "public"."sys_menu_map_power" VALUES ('42', '123');
INSERT INTO "public"."sys_menu_map_power" VALUES ('43', '130');
INSERT INTO "public"."sys_menu_map_power" VALUES ('43', '131');
INSERT INTO "public"."sys_menu_map_power" VALUES ('43', '132');
INSERT INTO "public"."sys_menu_map_power" VALUES ('43', '133');
INSERT INTO "public"."sys_menu_map_power" VALUES ('62', '150');
INSERT INTO "public"."sys_menu_map_power" VALUES ('61', '143');
INSERT INTO "public"."sys_menu_map_power" VALUES ('61', '142');
INSERT INTO "public"."sys_menu_map_power" VALUES ('61', '141');
INSERT INTO "public"."sys_menu_map_power" VALUES ('61', '140');
INSERT INTO "public"."sys_menu_map_power" VALUES ('62', '151');
INSERT INTO "public"."sys_menu_map_power" VALUES ('62', '152');
INSERT INTO "public"."sys_menu_map_power" VALUES ('62', '153');
INSERT INTO "public"."sys_menu_map_power" VALUES ('71', '160');
INSERT INTO "public"."sys_menu_map_power" VALUES ('71', '161');
INSERT INTO "public"."sys_menu_map_power" VALUES ('71', '162');
INSERT INTO "public"."sys_menu_map_power" VALUES ('71', '163');
INSERT INTO "public"."sys_menu_map_power" VALUES ('91', '170');
INSERT INTO "public"."sys_menu_map_power" VALUES ('91', '171');
INSERT INTO "public"."sys_menu_map_power" VALUES ('91', '172');
INSERT INTO "public"."sys_menu_map_power" VALUES ('91', '173');
INSERT INTO "public"."sys_menu_map_power" VALUES ('114', '180');
INSERT INTO "public"."sys_menu_map_power" VALUES ('114', '181');
INSERT INTO "public"."sys_menu_map_power" VALUES ('114', '182');
INSERT INTO "public"."sys_menu_map_power" VALUES ('114', '183');
INSERT INTO "public"."sys_menu_map_power" VALUES ('113', '190');
INSERT INTO "public"."sys_menu_map_power" VALUES ('113', '191');
INSERT INTO "public"."sys_menu_map_power" VALUES ('113', '192');
INSERT INTO "public"."sys_menu_map_power" VALUES ('113', '193');
INSERT INTO "public"."sys_menu_map_power" VALUES ('112', '200');
INSERT INTO "public"."sys_menu_map_power" VALUES ('112', '201');
INSERT INTO "public"."sys_menu_map_power" VALUES ('112', '202');
INSERT INTO "public"."sys_menu_map_power" VALUES ('112', '203');
INSERT INTO "public"."sys_menu_map_power" VALUES ('110', '210');
INSERT INTO "public"."sys_menu_map_power" VALUES ('110', '211');
INSERT INTO "public"."sys_menu_map_power" VALUES ('110', '212');
INSERT INTO "public"."sys_menu_map_power" VALUES ('110', '213');

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_power";
CREATE TABLE "public"."sys_power" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "coder" varchar(30) COLLATE "pg_catalog"."default",
  "menu_id" varchar(32) COLLATE "pg_catalog"."default",
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "creare_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(32) COLLATE "pg_catalog"."default",
  "udpare_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."sys_power"."id" IS '权限id';
COMMENT ON COLUMN "public"."sys_power"."name" IS '权限名称';
COMMENT ON COLUMN "public"."sys_power"."coder" IS '权限编码';
COMMENT ON COLUMN "public"."sys_power"."menu_id" IS '菜单id';
COMMENT ON COLUMN "public"."sys_power"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."sys_power"."creare_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_power"."update_user" IS '修改时间';
COMMENT ON COLUMN "public"."sys_power"."udpare_time" IS '修改时间';
COMMENT ON TABLE "public"."sys_power" IS '权限表';

-- ----------------------------
-- Records of sys_power
-- ----------------------------
INSERT INTO "public"."sys_power" VALUES ('171', '修改流程', 'act-modify', '91', 'root', '2020-03-06 14:50:09.9826', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('90', '添加部门文档', 'dept-add', '22', 'root', '2020-03-06 12:11:33.7426', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('91', '修改部门文档', 'dept-modify', '22', 'root', '2020-03-06 12:11:37.5016', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('92', '删除部门文档', 'dept-remove', '22', 'root', '2020-03-06 12:11:40.7686', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('30', '添加角色', 'role-add', '13', 'root', '2020-03-06 11:41:49.6426', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('31', '修改角色', 'role-modify', '13', 'root', '2020-03-06 11:41:56.1296', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('32', '删除角色', 'role-remove', '13', 'root', '2020-03-06 11:44:51.2406', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('93', '查询部门文档', 'dept-find', '22', 'root', '2020-03-06 12:11:44.0766', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('94', '下载部门文档', 'dept-down', '22', 'root', '2020-03-06 12:11:47.3936', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('95', '转存部门文档', 'dept-move', '22', 'root', '2020-03-06 12:11:52.5146', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('100', '添加个人文档', 'personal-add', '23', 'root', '2020-03-06 12:14:17.8496', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('101', '修改个人文档', 'personal-modify', '23', 'root', '2020-03-06 12:14:25.1496', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('103', '查询个人文档', 'personal-find', '23', 'root', '2020-03-06 12:14:35.1556', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('102', '删除个人文档', 'personal-remove', '23', 'root', '2020-03-06 12:14:30.4526', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('104', '下载个人文档', 'personal-down', '23', 'root', '2020-03-06 12:14:48.8806', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('172', '删除流程', 'act-remove', '91', 'root', '2020-03-06 14:50:13.8886', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('142', '删除话题圈子', 'circle-remove', '61', 'root', '2020-03-06 12:31:35.9116', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('173', '查询流程', 'act-find', '91', 'root', '2020-03-06 14:50:18.6506', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('33', '查询角色', 'role-find', '13', 'root', '2020-03-06 11:45:31.5426', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('40', '添加用户', 'user-add', '14', 'root', '2020-03-06 11:46:53.6846', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('41', '修改用户', 'user-modify', '14', 'root', '2020-03-06 11:49:38.1006', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('42', '删除用户', 'user-remove', '14', 'root', '2020-03-06 11:49:52.9906', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('43', '查询用户', 'user-find', '14', 'root', '2020-03-06 11:49:56.9626', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('44', '导出用户', 'user-output', '14', 'root', '2020-03-06 11:50:56.9326', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('180', '添加题库', 'subject-add', '114', 'root', '2020-03-06 15:17:36.9766', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('143', '查询话题圈子', 'circle-find', '61', 'root', '2020-03-06 12:31:39.9626', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('150', '添加话题', 'article-add', '62', 'root', '2020-03-06 12:34:51.8026', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('151', '修改话题', 'article-modify', '62', 'root', '2020-03-06 12:34:54.9186', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('152', '删除话题', 'article-remove', '62', 'root', '2020-03-06 12:34:57.8666', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('105', '转存个人文档', 'personal-move', '23', 'root', '2020-03-06 12:14:59.8126', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('50', '添加部门', 'dept-add', '15', 'root', '2020-03-06 11:52:28.7156', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('51', '修改部门', 'dept-modify', '15', 'root', '2020-03-06 11:52:32.5126', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('52', '删除部门', 'dept-remove', '15', 'root', '2020-03-06 11:52:36.0206', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('53', '查询部门', 'dept-find', '15', 'root', '2020-03-06 11:52:39.5936', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('110', '添加知识分类', 'knowledge-add', '41', 'root', '2020-03-06 12:24:23.0516', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('111', '修改知识分类', 'knowledge-modify', '41', 'root', '2020-03-06 12:24:27.3786', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('54', '导出部门', 'dept-output', '15', 'root', '2020-03-06 11:52:52.3286', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('60', '添加字典', 'dict-add', '16', 'root', '2020-03-06 11:55:27.6296', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('61', '修改字典', 'dict-modify', '16', 'root', '2020-03-06 11:55:32.6886', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('62', '删除字典', 'dict-remove', '16', 'root', '2020-03-06 11:55:36.0626', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('63', '查询字典', 'dict-find', '16', 'root', '2020-03-06 11:55:40.1406', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('64', '导出字典', 'dict-output', '16', 'root', '2020-03-06 11:55:44.1786', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('73', '查询日志', 'log-find', '17', 'root', '2020-03-06 11:59:38.3346', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('74', '导出日志', 'log-output', '17', 'root', '2020-03-06 11:59:42.0366', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('80', '添加公司文档', 'company-add', '21', 'root', '2020-03-06 12:03:44.9686', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('81', '修改公司文档', 'company-modify', '21', 'root', '2020-03-06 12:03:53.7176', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('82', '删除公司文档', 'company-remove', '21', 'root', '2020-03-06 12:03:57.2156', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('83', '查询公司文档', 'company-find', '21', 'root', '2020-03-06 12:04:00.9336', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('84', '下载公司文档', 'company-down', '21', 'root', '2020-03-06 12:04:04.9186', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('85', '转存公司文档', 'company-move', '21', 'root', '2020-03-06 12:05:01.8476', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('153', '查询话题', 'article-find', '62', 'root', '2020-03-06 12:35:01.8316', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('160', '添加项目', 'project-add', '71', 'root', '2020-03-06 12:37:52.3896', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('161', '修改项目', 'project-modify', '71', 'root', '2020-03-06 12:38:00.6136', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('162', '删除项目', 'project-remove', '71', 'root', '2020-03-06 12:38:04.0466', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('163', '查询项目', 'project-find', '71', 'root', '2020-03-06 12:38:45.1866', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('192', '删除试卷', 'test_paper-remove', '113', 'root', '2020-03-06 15:21:43.9116', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('200', '添加考试', 'exam-add', '112', 'root', '2020-03-06 15:23:21.9186', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('112', '删除知识分类', 'knowledge-remove', '41', 'root', '2020-03-06 12:24:30.6656', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('113', '查询知识分类', 'knowledge-find', '41', 'root', '2020-03-06 12:24:34.1986', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('120', '添加精品文档', 'quality-add', '42', 'root', '2020-03-06 12:27:08.1976', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('121', '修改精品文档', 'quality-modify', '42', 'root', '2020-03-06 12:27:11.9456', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('122', '删除精品文档', 'quality-remove', '42', 'root', '2020-03-06 12:27:18.6626', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('193', '查询试卷', 'test_paper-find', '113', 'root', '2020-03-06 15:21:47.9036', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('123', '查询精品文档', 'quality-find', '42', 'root', '2020-03-06 12:27:23.5836', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('130', '添加动态图', 'dynamic-add', '43', 'root', '2020-03-06 12:29:21.8236', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('131', '修改动态图', 'dynamic-modify', '43', 'root', '2020-03-06 12:29:25.4826', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('132', '删除动态图', 'dynamic-remove', '43', 'root', '2020-03-06 12:29:29.0706', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('133', '查询动态图', 'dynamic-find', '43', 'root', '2020-03-06 12:29:34.7516', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('140', '添加话题圈子', 'circle-add', '61', 'root', '2020-03-06 12:31:22.7406', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('141', '修改话题圈子', 'circle-modify', '61', 'root', '2020-03-06 12:31:31.7576', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('181', '修改题库', 'subject-modify', '114', 'root', '2020-03-06 15:17:40.4206', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('202', '删除考试', 'exam-remove', '112', 'root', '2020-03-06 15:23:48.3216', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('203', '查询考试', 'exam-find', '112', 'root', '2020-03-06 15:24:02.4896', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('182', '删除题库', 'subject-remove', '114', 'root', '2020-03-06 15:17:43.8526', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('183', '查询题库', 'subject-find', '114', 'root', '2020-03-06 15:17:50.9896', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('210', '添加课程', 'curriculum-add', '111', 'root', '2020-03-06 15:25:42.0236', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('170', '添加流程', 'act-add', '91', 'root', '2020-03-06 14:50:05.7796', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('191', '修改试卷', 'test_paper-modify', '113', 'root', '2020-03-06 15:21:39.9556', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('201', '修改考试', 'exam-modify', '112', 'root', '2020-03-06 15:23:43.8236', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('211', '修改课程', 'curriculum-modify', '111', 'root', '2020-03-06 15:25:47.7546', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('213', '查询课程', 'curriculum-find', '111', 'root', '2020-03-06 15:25:58.2386', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('212', '删除课程', 'curriculum-remove', '111', 'root', '2020-03-06 15:25:52.1446', NULL, NULL);
INSERT INTO "public"."sys_power" VALUES ('190', '添加试卷', 'test_paper-add', '113', 'root', '2020-03-06 15:21:36.1526', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role";
CREATE TABLE "public"."sys_role" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "status" int2 DEFAULT 1,
  "describe" varchar(300) COLLATE "pg_catalog"."default",
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."sys_role"."id" IS '角色id';
COMMENT ON COLUMN "public"."sys_role"."name" IS '角色名称';
COMMENT ON COLUMN "public"."sys_role"."status" IS '角色状态（0：禁用；1：启用）';
COMMENT ON COLUMN "public"."sys_role"."describe" IS '角色描述';
COMMENT ON COLUMN "public"."sys_role"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."sys_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_role"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."sys_role"."update_time" IS '修改时间';
COMMENT ON TABLE "public"."sys_role" IS '角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "public"."sys_role" VALUES ('1', '系统管理', 1, '系统维护最高权限', NULL, '2020-03-06 09:48:24.9706', 'root', '2020-04-03 14:23:44.518');
INSERT INTO "public"."sys_role" VALUES ('95e8c76ad5c54f0896a63907af4909fe', '测试1', 1, '测试1', 'root', '2020-03-13 17:41:42.501778', NULL, NULL);
INSERT INTO "public"."sys_role" VALUES ('6962d0018f4d48d689f171f9235e53c5', 'ccc', 0, 'xxxx', 'root', '2020-03-16 17:30:48.851018', 'root', '2020-03-25 14:37:12.733');
INSERT INTO "public"."sys_role" VALUES ('5220b533d6ff451eb98a6de76b6f272f', 'ceshi', 1, 'ceshi', 'root', '2020-03-25 14:49:03.267696', NULL, NULL);
INSERT INTO "public"."sys_role" VALUES ('f30a67409a05479799571cc2423e977a', '测试', 0, '测试', 'root', '2020-03-17 09:05:05.127619', 'root', '2020-03-25 14:43:02.249');

-- ----------------------------
-- Table structure for sys_role_map_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_map_menu";
CREATE TABLE "public"."sys_role_map_menu" (
  "role_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "menu_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."sys_role_map_menu"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."sys_role_map_menu"."menu_id" IS '菜单id';
COMMENT ON TABLE "public"."sys_role_map_menu" IS '角色菜单表';

-- ----------------------------
-- Records of sys_role_map_menu
-- ----------------------------
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '44');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '230');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '110');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '111');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '112');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '113');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '114');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '90');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '91');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '50');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '10');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '13');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '14');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '15');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '17');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '240');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '120');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '121');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '122');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '200');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '123');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '400');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '600');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '800');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '60');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '61');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '62');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '63');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '20');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '64');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '21');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '22');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '23');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '70');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '71');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '72');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '73');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '30');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '220');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '100');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '101');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '300');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '102');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '500');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '700');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '80');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '900');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '81');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '82');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '40');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '41');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '42');
INSERT INTO "public"."sys_role_map_menu" VALUES ('6962d0018f4d48d689f171f9235e53c5', '43');
INSERT INTO "public"."sys_role_map_menu" VALUES ('5220b533d6ff451eb98a6de76b6f272f', '700');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '44');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '230');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '110');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '111');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '112');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '113');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '114');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '90');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '91');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '50');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '10');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '13');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', 'e34b8eb18de243808385baab6419c21f');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '14');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '15');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '17');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '240');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '120');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '121');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '122');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '200');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '123');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '7c19976e01b5483a8c257c0fb3718b89');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '400');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '600');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '800');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '60');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '61');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '62');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '63');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '64');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '20');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '21');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '22');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '23');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '9add2f57b7a64163a0a063cfc9f6fcb7');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '70');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '71');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '72');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '73');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '30');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '220');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '100');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '101');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '300');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '102');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '500');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '700');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '80');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '900');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '81');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '82');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '40');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '41');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '42');
INSERT INTO "public"."sys_role_map_menu" VALUES ('1', '43');

-- ----------------------------
-- Table structure for sys_role_map_power
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_map_power";
CREATE TABLE "public"."sys_role_map_power" (
  "role_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "power_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."sys_role_map_power"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."sys_role_map_power"."power_id" IS '权限id';
COMMENT ON TABLE "public"."sys_role_map_power" IS '角色权限关系表';

-- ----------------------------
-- Records of sys_role_map_power
-- ----------------------------
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '44');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '190');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '191');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '192');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '193');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '150');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '151');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '152');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '153');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '110');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '111');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '112');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '113');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '90');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '91');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '92');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '93');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '50');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '94');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '51');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '95');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '52');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '53');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '54');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '160');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '161');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '162');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '163');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '120');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '121');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '122');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '123');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '200');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '201');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '202');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '203');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '60');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '61');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '62');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '63');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '64');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '170');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '171');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '172');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '173');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '130');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '131');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '132');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '133');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '210');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '211');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '212');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '213');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '73');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '30');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '74');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '31');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '32');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '33');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '180');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '181');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '182');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '183');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '140');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '141');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '142');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '143');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '220');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '100');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '221');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '101');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '222');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '102');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '223');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '103');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '104');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '105');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '80');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '81');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '82');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '83');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '40');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '84');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '41');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '85');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '42');
INSERT INTO "public"."sys_role_map_power" VALUES ('1', '43');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user";
CREATE TABLE "public"."sys_user" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "nick_name" varchar(30) COLLATE "pg_catalog"."default",
  "name" varchar(30) COLLATE "pg_catalog"."default",
  "password" varchar(32) COLLATE "pg_catalog"."default",
  "phone" varchar(11) COLLATE "pg_catalog"."default",
  "status" int2 DEFAULT 1,
  "dept_id" varchar(32) COLLATE "pg_catalog"."default",
  "role_id" varchar(32) COLLATE "pg_catalog"."default",
  "portrait" varchar(300) COLLATE "pg_catalog"."default",
  "create_user" varchar(30) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_user" varchar(30) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "sex" int2 DEFAULT 0,
  "type" int2 DEFAULT 0,
  "mail" varchar(30) COLLATE "pg_catalog"."default",
  "weight" int2 DEFAULT 0
)
;
COMMENT ON COLUMN "public"."sys_user"."id" IS '用户id';
COMMENT ON COLUMN "public"."sys_user"."nick_name" IS '用户昵称';
COMMENT ON COLUMN "public"."sys_user"."name" IS '用户名（工号）';
COMMENT ON COLUMN "public"."sys_user"."password" IS '用户密码';
COMMENT ON COLUMN "public"."sys_user"."phone" IS '联系方式';
COMMENT ON COLUMN "public"."sys_user"."status" IS '用户状态（0：禁用；1：启用）';
COMMENT ON COLUMN "public"."sys_user"."dept_id" IS '部门id';
COMMENT ON COLUMN "public"."sys_user"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."sys_user"."portrait" IS '用户头像';
COMMENT ON COLUMN "public"."sys_user"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."sys_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_user"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."sys_user"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."sys_user"."sex" IS '用户性别（0：男；1：女）';
COMMENT ON COLUMN "public"."sys_user"."type" IS '用户类型（0：自定义用户；1：系统用户）';
COMMENT ON COLUMN "public"."sys_user"."mail" IS '用户邮件';
COMMENT ON COLUMN "public"."sys_user"."weight" IS '用户权重（数值越大，查看权限越大）';
COMMENT ON TABLE "public"."sys_user" IS '用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES ('cde659391d7944b19041be284ca7bccc', '张三4', '1004', '888888', '15832654792', 1, '3f5c14867adf44bd9b30e38c06eab067', 'f30a67409a05479799571cc2423e977a', NULL, 'root', '2020-03-16 14:34:33.348469', 'root', '2020-03-24 11:20:48.837', 0, 0, '114@qq.com', 0);
INSERT INTO "public"."sys_user" VALUES ('c6750204c06945fe8bc4a82d48e18d64', '张三5', '1005', '888888', '15832654793', 1, '60b2b7b3b42d4f1485e27a9e8b715dac', '95e8c76ad5c54f0896a63907af4909fe', NULL, 'root', '2020-03-16 14:34:33.348469', 'root', '2020-03-24 11:21:01.208', 0, 0, '115@qq.com', 0);
INSERT INTO "public"."sys_user" VALUES ('9e2b3bc267144195936b2ed08b8863f1', '张三3', '1003', '888888', '15832654791', 1, '53ee053db77a40e38606fdd401900e16', '6962d0018f4d48d689f171f9235e53c5', NULL, 'root', '2020-03-16 14:34:33.348469', 'root', '2020-03-24 11:21:08.321', 0, 0, '113@qq.com', 0);
INSERT INTO "public"."sys_user" VALUES ('5450ef6e0bb64c9aaec88527e77ffb49', '张三1', '1001', '888888', '15832654789', 1, '3f5c14867adf44bd9b30e38c06eab067', '95e8c76ad5c54f0896a63907af4909fe', NULL, 'root', '2020-03-16 14:34:33.348469', 'root', '2020-03-24 11:21:16.397', 0, 0, '111@qq.com', 0);
INSERT INTO "public"."sys_user" VALUES ('44ca0fe1a562407eb76ae084fd129fc1', '张三2', '1002', '888888', '15832654790', 1, '53ee053db77a40e38606fdd401900e16', '6962d0018f4d48d689f171f9235e53c5', NULL, 'root', '2020-03-16 14:34:33.348469', 'root', '2020-03-24 11:21:23.301', 0, 0, '112@qq.com', 0);
INSERT INTO "public"."sys_user" VALUES ('11845c14c31c44ea929de313ad194feb', '张三6', '1006', '888888', '15832654794', 1, '3f5c14867adf44bd9b30e38c06eab067', 'f30a67409a05479799571cc2423e977a', NULL, 'root', '2020-03-16 14:34:33.348469', 'root', '2020-03-24 11:21:30.261', 0, 0, '116@qq.com', 0);
INSERT INTO "public"."sys_user" VALUES ('469daad359b34676ade85832de90d517', '王五', '000001', NULL, '111111', 1, NULL, NULL, NULL, NULL, '2020-03-25 15:16:54.77025', NULL, NULL, 0, 0, NULL, 0);
INSERT INTO "public"."sys_user" VALUES ('8f705294e8c348bfa22602c1c48db433', '王五1', '0000011', NULL, '1111111', 1, NULL, NULL, NULL, NULL, '2020-03-25 15:16:54.77025', NULL, NULL, 1, 0, NULL, 0);
INSERT INTO "public"."sys_user" VALUES ('1', 'root', 'root', '123456', '15636548795', 1, '1', '1', NULL, NULL, '2020-03-06 00:00:00', 'root', '2020-03-30 11:10:38.532', 0, 1, '1300115207@qq.com', 0);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."act_evt_log_log_nr__seq"
OWNED BY "public"."act_evt_log"."log_nr_";
SELECT setval('"public"."act_evt_log_log_nr__seq"', 2, false);
ALTER SEQUENCE "public"."base_circle_id_seq"
OWNED BY "public"."base_circle"."id";
SELECT setval('"public"."base_circle_id_seq"', 4, false);
ALTER SEQUENCE "public"."base_curriculum_id_seq"
OWNED BY "public"."base_curriculum"."id";
SELECT setval('"public"."base_curriculum_id_seq"', 4, false);
ALTER SEQUENCE "public"."base_document_category_id_seq"
OWNED BY "public"."base_document_category"."id";
SELECT setval('"public"."base_document_category_id_seq"', 4, false);
ALTER SEQUENCE "public"."base_document_id_seq"
OWNED BY "public"."base_document"."id";
SELECT setval('"public"."base_document_id_seq"', 4, false);
ALTER SEQUENCE "public"."base_subject_id_seq"
OWNED BY "public"."base_subject"."id";
SELECT setval('"public"."base_subject_id_seq"', 4, false);
ALTER SEQUENCE "public"."base_test paper_id_seq"
OWNED BY "public"."base_test_paper"."id";
SELECT setval('"public"."base_test paper_id_seq"', 4, false);
ALTER SEQUENCE "public"."bus_project_id_seq"
OWNED BY "public"."bus_project"."id";
SELECT setval('"public"."bus_project_id_seq"', 4, false);
ALTER SEQUENCE "public"."sys_dept_id_seq"
OWNED BY "public"."sys_dept"."id";
SELECT setval('"public"."sys_dept_id_seq"', 4, false);
ALTER SEQUENCE "public"."sys_dict_id_seq"
OWNED BY "public"."sys_dict"."id";
SELECT setval('"public"."sys_dict_id_seq"', 4, false);
ALTER SEQUENCE "public"."sys_log_id_seq"
OWNED BY "public"."sys_log"."id";
SELECT setval('"public"."sys_log_id_seq"', 4, false);
ALTER SEQUENCE "public"."sys_power_id_seq"
OWNED BY "public"."sys_power"."id";
SELECT setval('"public"."sys_power_id_seq"', 4, false);
ALTER SEQUENCE "public"."sys_role_id_seq"
OWNED BY "public"."sys_role"."id";
SELECT setval('"public"."sys_role_id_seq"', 4, false);
ALTER SEQUENCE "public"."sys_user_id_seq"
OWNED BY "public"."sys_user"."id";
SELECT setval('"public"."sys_user_id_seq"', 4, false);

-- ----------------------------
-- Primary Key structure for table act_evt_log
-- ----------------------------
ALTER TABLE "public"."act_evt_log" ADD CONSTRAINT "act_evt_log_pkey" PRIMARY KEY ("log_nr_");

-- ----------------------------
-- Indexes structure for table act_ge_bytearray
-- ----------------------------
CREATE INDEX "act_idx_bytear_depl" ON "public"."act_ge_bytearray" USING btree (
  "deployment_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ge_bytearray
-- ----------------------------
ALTER TABLE "public"."act_ge_bytearray" ADD CONSTRAINT "act_ge_bytearray_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_ge_property
-- ----------------------------
ALTER TABLE "public"."act_ge_property" ADD CONSTRAINT "act_ge_property_pkey" PRIMARY KEY ("name_");

-- ----------------------------
-- Indexes structure for table act_hi_actinst
-- ----------------------------
CREATE INDEX "act_idx_hi_act_inst_end" ON "public"."act_hi_actinst" USING btree (
  "end_time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_act_inst_exec" ON "public"."act_hi_actinst" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "act_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_act_inst_procinst" ON "public"."act_hi_actinst" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "act_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_act_inst_start" ON "public"."act_hi_actinst" USING btree (
  "start_time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_actinst
-- ----------------------------
ALTER TABLE "public"."act_hi_actinst" ADD CONSTRAINT "act_hi_actinst_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_hi_attachment
-- ----------------------------
ALTER TABLE "public"."act_hi_attachment" ADD CONSTRAINT "act_hi_attachment_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_hi_comment
-- ----------------------------
ALTER TABLE "public"."act_hi_comment" ADD CONSTRAINT "act_hi_comment_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_detail
-- ----------------------------
CREATE INDEX "act_idx_hi_detail_act_inst" ON "public"."act_hi_detail" USING btree (
  "act_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_detail_name" ON "public"."act_hi_detail" USING btree (
  "name_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_detail_proc_inst" ON "public"."act_hi_detail" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_detail_task_id" ON "public"."act_hi_detail" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_detail_time" ON "public"."act_hi_detail" USING btree (
  "time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_detail
-- ----------------------------
ALTER TABLE "public"."act_hi_detail" ADD CONSTRAINT "act_hi_detail_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_identitylink
-- ----------------------------
CREATE INDEX "act_idx_hi_ident_lnk_procinst" ON "public"."act_hi_identitylink" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_ident_lnk_task" ON "public"."act_hi_identitylink" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_ident_lnk_user" ON "public"."act_hi_identitylink" USING btree (
  "user_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_identitylink
-- ----------------------------
ALTER TABLE "public"."act_hi_identitylink" ADD CONSTRAINT "act_hi_identitylink_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_procinst
-- ----------------------------
CREATE INDEX "act_idx_hi_pro_i_buskey" ON "public"."act_hi_procinst" USING btree (
  "business_key_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_pro_inst_end" ON "public"."act_hi_procinst" USING btree (
  "end_time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table act_hi_procinst
-- ----------------------------
ALTER TABLE "public"."act_hi_procinst" ADD CONSTRAINT "act_hi_procinst_proc_inst_id__key" UNIQUE ("proc_inst_id_");

-- ----------------------------
-- Primary Key structure for table act_hi_procinst
-- ----------------------------
ALTER TABLE "public"."act_hi_procinst" ADD CONSTRAINT "act_hi_procinst_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_taskinst
-- ----------------------------
CREATE INDEX "act_idx_hi_task_inst_procinst" ON "public"."act_hi_taskinst" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_taskinst
-- ----------------------------
ALTER TABLE "public"."act_hi_taskinst" ADD CONSTRAINT "act_hi_taskinst_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_varinst
-- ----------------------------
CREATE INDEX "act_idx_hi_procvar_name_type" ON "public"."act_hi_varinst" USING btree (
  "name_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "var_type_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_procvar_proc_inst" ON "public"."act_hi_varinst" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_procvar_task_id" ON "public"."act_hi_varinst" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_varinst
-- ----------------------------
ALTER TABLE "public"."act_hi_varinst" ADD CONSTRAINT "act_hi_varinst_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_id_group
-- ----------------------------
ALTER TABLE "public"."act_id_group" ADD CONSTRAINT "act_id_group_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_id_info
-- ----------------------------
ALTER TABLE "public"."act_id_info" ADD CONSTRAINT "act_id_info_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_id_membership
-- ----------------------------
CREATE INDEX "act_idx_memb_group" ON "public"."act_id_membership" USING btree (
  "group_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_memb_user" ON "public"."act_id_membership" USING btree (
  "user_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_id_membership
-- ----------------------------
ALTER TABLE "public"."act_id_membership" ADD CONSTRAINT "act_id_membership_pkey" PRIMARY KEY ("user_id_", "group_id_");

-- ----------------------------
-- Primary Key structure for table act_id_user
-- ----------------------------
ALTER TABLE "public"."act_id_user" ADD CONSTRAINT "act_id_user_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_procdef_info
-- ----------------------------
CREATE INDEX "act_idx_procdef_info_json" ON "public"."act_procdef_info" USING btree (
  "info_json_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_procdef_info_proc" ON "public"."act_procdef_info" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table act_procdef_info
-- ----------------------------
ALTER TABLE "public"."act_procdef_info" ADD CONSTRAINT "act_uniq_info_procdef" UNIQUE ("proc_def_id_");

-- ----------------------------
-- Primary Key structure for table act_procdef_info
-- ----------------------------
ALTER TABLE "public"."act_procdef_info" ADD CONSTRAINT "act_procdef_info_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_re_deployment
-- ----------------------------
ALTER TABLE "public"."act_re_deployment" ADD CONSTRAINT "act_re_deployment_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_re_model
-- ----------------------------
CREATE INDEX "act_idx_model_deployment" ON "public"."act_re_model" USING btree (
  "deployment_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_model_source" ON "public"."act_re_model" USING btree (
  "editor_source_value_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_model_source_extra" ON "public"."act_re_model" USING btree (
  "editor_source_extra_value_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_re_model
-- ----------------------------
ALTER TABLE "public"."act_re_model" ADD CONSTRAINT "act_re_model_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Uniques structure for table act_re_procdef
-- ----------------------------
ALTER TABLE "public"."act_re_procdef" ADD CONSTRAINT "act_uniq_procdef" UNIQUE ("key_", "version_", "tenant_id_");

-- ----------------------------
-- Primary Key structure for table act_re_procdef
-- ----------------------------
ALTER TABLE "public"."act_re_procdef" ADD CONSTRAINT "act_re_procdef_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_deadletter_job
-- ----------------------------
CREATE INDEX "act_idx_deadletter_job_exception" ON "public"."act_ru_deadletter_job" USING btree (
  "exception_stack_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_deadletter_job_execution_id" ON "public"."act_ru_deadletter_job" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_deadletter_job_proc_def_id" ON "public"."act_ru_deadletter_job" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_deadletter_job_process_instance_id" ON "public"."act_ru_deadletter_job" USING btree (
  "process_instance_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_deadletter_job
-- ----------------------------
ALTER TABLE "public"."act_ru_deadletter_job" ADD CONSTRAINT "act_ru_deadletter_job_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_event_subscr
-- ----------------------------
CREATE INDEX "act_idx_event_subscr" ON "public"."act_ru_event_subscr" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_event_subscr_config_" ON "public"."act_ru_event_subscr" USING btree (
  "configuration_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_event_subscr
-- ----------------------------
ALTER TABLE "public"."act_ru_event_subscr" ADD CONSTRAINT "act_ru_event_subscr_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_execution
-- ----------------------------
CREATE INDEX "act_idx_exe_parent" ON "public"."act_ru_execution" USING btree (
  "parent_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_exe_procdef" ON "public"."act_ru_execution" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_exe_procinst" ON "public"."act_ru_execution" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_exe_root" ON "public"."act_ru_execution" USING btree (
  "root_proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_exe_super" ON "public"."act_ru_execution" USING btree (
  "super_exec_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_exec_buskey" ON "public"."act_ru_execution" USING btree (
  "business_key_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_execution
-- ----------------------------
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_ru_execution_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_identitylink
-- ----------------------------
CREATE INDEX "act_idx_athrz_procedef" ON "public"."act_ru_identitylink" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_ident_lnk_group" ON "public"."act_ru_identitylink" USING btree (
  "group_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_ident_lnk_user" ON "public"."act_ru_identitylink" USING btree (
  "user_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_idl_procinst" ON "public"."act_ru_identitylink" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_tskass_task" ON "public"."act_ru_identitylink" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_identitylink
-- ----------------------------
ALTER TABLE "public"."act_ru_identitylink" ADD CONSTRAINT "act_ru_identitylink_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_job
-- ----------------------------
CREATE INDEX "act_idx_job_exception" ON "public"."act_ru_job" USING btree (
  "exception_stack_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_job_execution_id" ON "public"."act_ru_job" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_job_proc_def_id" ON "public"."act_ru_job" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_job_process_instance_id" ON "public"."act_ru_job" USING btree (
  "process_instance_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_job
-- ----------------------------
ALTER TABLE "public"."act_ru_job" ADD CONSTRAINT "act_ru_job_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_suspended_job
-- ----------------------------
CREATE INDEX "act_idx_suspended_job_exception" ON "public"."act_ru_suspended_job" USING btree (
  "exception_stack_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_suspended_job_execution_id" ON "public"."act_ru_suspended_job" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_suspended_job_proc_def_id" ON "public"."act_ru_suspended_job" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_suspended_job_process_instance_id" ON "public"."act_ru_suspended_job" USING btree (
  "process_instance_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_suspended_job
-- ----------------------------
ALTER TABLE "public"."act_ru_suspended_job" ADD CONSTRAINT "act_ru_suspended_job_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_task
-- ----------------------------
CREATE INDEX "act_idx_task_create" ON "public"."act_ru_task" USING btree (
  "create_time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_task_exec" ON "public"."act_ru_task" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_task_procdef" ON "public"."act_ru_task" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_task_procinst" ON "public"."act_ru_task" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_task
-- ----------------------------
ALTER TABLE "public"."act_ru_task" ADD CONSTRAINT "act_ru_task_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_timer_job
-- ----------------------------
CREATE INDEX "act_idx_timer_job_exception" ON "public"."act_ru_timer_job" USING btree (
  "exception_stack_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_timer_job_execution_id" ON "public"."act_ru_timer_job" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_timer_job_proc_def_id" ON "public"."act_ru_timer_job" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_timer_job_process_instance_id" ON "public"."act_ru_timer_job" USING btree (
  "process_instance_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_timer_job
-- ----------------------------
ALTER TABLE "public"."act_ru_timer_job" ADD CONSTRAINT "act_ru_timer_job_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_variable
-- ----------------------------
CREATE INDEX "act_idx_var_bytearray" ON "public"."act_ru_variable" USING btree (
  "bytearray_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_var_exe" ON "public"."act_ru_variable" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_var_procinst" ON "public"."act_ru_variable" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_variable_task_id" ON "public"."act_ru_variable" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_variable
-- ----------------------------
ALTER TABLE "public"."act_ru_variable" ADD CONSTRAINT "act_ru_variable_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Uniques structure for table base_circle
-- ----------------------------
ALTER TABLE "public"."base_circle" ADD CONSTRAINT "circle_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table base_circle
-- ----------------------------
ALTER TABLE "public"."base_circle" ADD CONSTRAINT "base_circle_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table base_curriculum
-- ----------------------------
ALTER TABLE "public"."base_curriculum" ADD CONSTRAINT "cuurr_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table base_curriculum
-- ----------------------------
ALTER TABLE "public"."base_curriculum" ADD CONSTRAINT "base_curriculum_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table base_document
-- ----------------------------
ALTER TABLE "public"."base_document" ADD CONSTRAINT "base_document_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table base_document_category
-- ----------------------------
ALTER TABLE "public"."base_document_category" ADD CONSTRAINT "doc_cate_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table base_document_category
-- ----------------------------
ALTER TABLE "public"."base_document_category" ADD CONSTRAINT "base_document_category_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table base_exam
-- ----------------------------
ALTER TABLE "public"."base_exam" ADD CONSTRAINT "exam_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table base_exam
-- ----------------------------
ALTER TABLE "public"."base_exam" ADD CONSTRAINT "base_exam_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table base_journalism
-- ----------------------------
ALTER TABLE "public"."base_journalism" ADD CONSTRAINT "journalism_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table base_journalism
-- ----------------------------
ALTER TABLE "public"."base_journalism" ADD CONSTRAINT "base_journalism_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table base_notice
-- ----------------------------
ALTER TABLE "public"."base_notice" ADD CONSTRAINT "notice_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table base_notice
-- ----------------------------
ALTER TABLE "public"."base_notice" ADD CONSTRAINT "base_notice_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table base_statistics
-- ----------------------------
ALTER TABLE "public"."base_statistics" ADD CONSTRAINT "base_statistics_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table base_subject
-- ----------------------------
ALTER TABLE "public"."base_subject" ADD CONSTRAINT "subject_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table base_subject
-- ----------------------------
ALTER TABLE "public"."base_subject" ADD CONSTRAINT "base_subject_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table base_test_paper
-- ----------------------------
ALTER TABLE "public"."base_test_paper" ADD CONSTRAINT "test_paper_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table base_test_paper
-- ----------------------------
ALTER TABLE "public"."base_test_paper" ADD CONSTRAINT "base_test paper_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table base_topic
-- ----------------------------
ALTER TABLE "public"."base_topic" ADD CONSTRAINT "topic_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table bus_document_record
-- ----------------------------
ALTER TABLE "public"."bus_document_record" ADD CONSTRAINT "bus_document_record_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table bus_project
-- ----------------------------
ALTER TABLE "public"."bus_project" ADD CONSTRAINT "project_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table bus_project
-- ----------------------------
ALTER TABLE "public"."bus_project" ADD CONSTRAINT "bus_project_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table bus_project_stage
-- ----------------------------
ALTER TABLE "public"."bus_project_stage" ADD CONSTRAINT "bus_project_stage_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table bus_user_map_curriculum
-- ----------------------------
ALTER TABLE "public"."bus_user_map_curriculum" ADD CONSTRAINT "bus_user_curriculum_pkey" PRIMARY KEY ("user_id", "curriculum_id");

-- ----------------------------
-- Primary Key structure for table bus_user_map_exam
-- ----------------------------
ALTER TABLE "public"."bus_user_map_exam" ADD CONSTRAINT "bus_user_map_exam_pkey" PRIMARY KEY ("user_id", "exam_id");

-- ----------------------------
-- Uniques structure for table sys_dept
-- ----------------------------
ALTER TABLE "public"."sys_dept" ADD CONSTRAINT "dept_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table sys_dept
-- ----------------------------
ALTER TABLE "public"."sys_dept" ADD CONSTRAINT "sys_dept_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table sys_dict
-- ----------------------------
ALTER TABLE "public"."sys_dict" ADD CONSTRAINT "dict_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table sys_dict
-- ----------------------------
ALTER TABLE "public"."sys_dict" ADD CONSTRAINT "sys_dict_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_log
-- ----------------------------
ALTER TABLE "public"."sys_log" ADD CONSTRAINT "sys_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table sys_menu
-- ----------------------------
ALTER TABLE "public"."sys_menu" ADD CONSTRAINT "menu_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table sys_menu
-- ----------------------------
ALTER TABLE "public"."sys_menu" ADD CONSTRAINT "sys_menu_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_menu_map_power
-- ----------------------------
ALTER TABLE "public"."sys_menu_map_power" ADD CONSTRAINT "pk_sys_menu_map_power" PRIMARY KEY ("menu_id", "power_id");

-- ----------------------------
-- Uniques structure for table sys_power
-- ----------------------------
ALTER TABLE "public"."sys_power" ADD CONSTRAINT "power_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table sys_power
-- ----------------------------
ALTER TABLE "public"."sys_power" ADD CONSTRAINT "sys_power_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD CONSTRAINT "role_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD CONSTRAINT "sys_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_map_menu
-- ----------------------------
ALTER TABLE "public"."sys_role_map_menu" ADD CONSTRAINT "pk_sys_role_map_menu" PRIMARY KEY ("role_id", "menu_id");

-- ----------------------------
-- Primary Key structure for table sys_role_map_power
-- ----------------------------
ALTER TABLE "public"."sys_role_map_power" ADD CONSTRAINT "sys_role_map_power_pkey" PRIMARY KEY ("role_id", "power_id");

-- ----------------------------
-- Uniques structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "user_unique_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "sys_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table act_ge_bytearray
-- ----------------------------
ALTER TABLE "public"."act_ge_bytearray" ADD CONSTRAINT "act_fk_bytearr_depl" FOREIGN KEY ("deployment_id_") REFERENCES "act_re_deployment" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_id_membership
-- ----------------------------
ALTER TABLE "public"."act_id_membership" ADD CONSTRAINT "act_fk_memb_group" FOREIGN KEY ("group_id_") REFERENCES "act_id_group" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_id_membership" ADD CONSTRAINT "act_fk_memb_user" FOREIGN KEY ("user_id_") REFERENCES "act_id_user" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_procdef_info
-- ----------------------------
ALTER TABLE "public"."act_procdef_info" ADD CONSTRAINT "act_fk_info_json_ba" FOREIGN KEY ("info_json_id_") REFERENCES "act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_procdef_info" ADD CONSTRAINT "act_fk_info_procdef" FOREIGN KEY ("proc_def_id_") REFERENCES "act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_re_model
-- ----------------------------
ALTER TABLE "public"."act_re_model" ADD CONSTRAINT "act_fk_model_deployment" FOREIGN KEY ("deployment_id_") REFERENCES "act_re_deployment" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_re_model" ADD CONSTRAINT "act_fk_model_source" FOREIGN KEY ("editor_source_value_id_") REFERENCES "act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_re_model" ADD CONSTRAINT "act_fk_model_source_extra" FOREIGN KEY ("editor_source_extra_value_id_") REFERENCES "act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_ru_deadletter_job
-- ----------------------------
ALTER TABLE "public"."act_ru_deadletter_job" ADD CONSTRAINT "act_fk_deadletter_job_exception" FOREIGN KEY ("exception_stack_id_") REFERENCES "act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_deadletter_job" ADD CONSTRAINT "act_fk_deadletter_job_execution" FOREIGN KEY ("execution_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_deadletter_job" ADD CONSTRAINT "act_fk_deadletter_job_proc_def" FOREIGN KEY ("proc_def_id_") REFERENCES "act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_deadletter_job" ADD CONSTRAINT "act_fk_deadletter_job_process_instance" FOREIGN KEY ("process_instance_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_ru_event_subscr
-- ----------------------------
ALTER TABLE "public"."act_ru_event_subscr" ADD CONSTRAINT "act_fk_event_exec" FOREIGN KEY ("execution_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_ru_execution
-- ----------------------------
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_fk_exe_parent" FOREIGN KEY ("parent_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_fk_exe_procdef" FOREIGN KEY ("proc_def_id_") REFERENCES "act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_fk_exe_procinst" FOREIGN KEY ("proc_inst_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_fk_exe_super" FOREIGN KEY ("super_exec_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_ru_identitylink
-- ----------------------------
ALTER TABLE "public"."act_ru_identitylink" ADD CONSTRAINT "act_fk_athrz_procedef" FOREIGN KEY ("proc_def_id_") REFERENCES "act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_identitylink" ADD CONSTRAINT "act_fk_idl_procinst" FOREIGN KEY ("proc_inst_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_identitylink" ADD CONSTRAINT "act_fk_tskass_task" FOREIGN KEY ("task_id_") REFERENCES "act_ru_task" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_ru_job
-- ----------------------------
ALTER TABLE "public"."act_ru_job" ADD CONSTRAINT "act_fk_job_exception" FOREIGN KEY ("exception_stack_id_") REFERENCES "act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_job" ADD CONSTRAINT "act_fk_job_execution" FOREIGN KEY ("execution_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_job" ADD CONSTRAINT "act_fk_job_proc_def" FOREIGN KEY ("proc_def_id_") REFERENCES "act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_job" ADD CONSTRAINT "act_fk_job_process_instance" FOREIGN KEY ("process_instance_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_ru_suspended_job
-- ----------------------------
ALTER TABLE "public"."act_ru_suspended_job" ADD CONSTRAINT "act_fk_suspended_job_exception" FOREIGN KEY ("exception_stack_id_") REFERENCES "act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_suspended_job" ADD CONSTRAINT "act_fk_suspended_job_execution" FOREIGN KEY ("execution_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_suspended_job" ADD CONSTRAINT "act_fk_suspended_job_proc_def" FOREIGN KEY ("proc_def_id_") REFERENCES "act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_suspended_job" ADD CONSTRAINT "act_fk_suspended_job_process_instance" FOREIGN KEY ("process_instance_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_ru_task
-- ----------------------------
ALTER TABLE "public"."act_ru_task" ADD CONSTRAINT "act_fk_task_exe" FOREIGN KEY ("execution_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_task" ADD CONSTRAINT "act_fk_task_procdef" FOREIGN KEY ("proc_def_id_") REFERENCES "act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_task" ADD CONSTRAINT "act_fk_task_procinst" FOREIGN KEY ("proc_inst_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_ru_timer_job
-- ----------------------------
ALTER TABLE "public"."act_ru_timer_job" ADD CONSTRAINT "act_fk_timer_job_exception" FOREIGN KEY ("exception_stack_id_") REFERENCES "act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_timer_job" ADD CONSTRAINT "act_fk_timer_job_execution" FOREIGN KEY ("execution_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_timer_job" ADD CONSTRAINT "act_fk_timer_job_proc_def" FOREIGN KEY ("proc_def_id_") REFERENCES "act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_timer_job" ADD CONSTRAINT "act_fk_timer_job_process_instance" FOREIGN KEY ("process_instance_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table act_ru_variable
-- ----------------------------
ALTER TABLE "public"."act_ru_variable" ADD CONSTRAINT "act_fk_var_bytearray" FOREIGN KEY ("bytearray_id_") REFERENCES "act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_variable" ADD CONSTRAINT "act_fk_var_exe" FOREIGN KEY ("execution_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_variable" ADD CONSTRAINT "act_fk_var_procinst" FOREIGN KEY ("proc_inst_id_") REFERENCES "act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
