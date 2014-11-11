package com.saituo.order.service.account;

//import com.google.common.collect.Lists;
//import com.saituo.order.commons.enumeration.entity.ResourceType;
//import com.saituo.order.service.account.AccountService;
//import com.saituo.order.test.service.ServiceTestCaseSupport;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
///**
// * 资源业务逻辑测试类
// *
// */
//public class ResourceServiceTest extends ServiceTestCaseSupport {
//
//    @Autowired
//    private AccountService accountService;
//
//    @Test
//    public void testGetResource(){
//        Map<String,Object> resource = accountService.getResource(1L);
//
//        assertEquals(resource.get("id"), 1L);
//        assertEquals(resource.get("name"), "权限管理");
//
//        resource = accountService.getResource(99L);
//        assertNull(resource);
//    }
//
//    @Test
//    public void testDeleteResources(){
//        int before = countRowsInTable("tb_resource");
//        int beforeGroupAssociation  = countRowsInTable("tb_group_resource");
//        accountService.deleteResources(Lists.newArrayList(1L));
//
//        int after = countRowsInTable("tb_resource");
//        int afterGroupAssociation  = countRowsInTable("tb_group_resource");
//
//        assertEquals(before - 13, after);
//        assertEquals(beforeGroupAssociation - 20, afterGroupAssociation);
//
//        accountService.deleteResources(Lists.newArrayList(99L));
//
//        assertEquals(after, countRowsInTable("tb_resource"));
//    }
//
//    @Test
//    public void testGetResources(){
//        assertEquals(accountService.getResources().size(), 22);
//        assertEquals(accountService.getResources(1L,2L,3L).size(), 19);
//    }
//
//    @Test
//    public void testSaveResource(){
//        Map<String,Object> resource = accountService.getResource(2L);
//
//        resource.remove("id");
//        resource.put("name","test resource");
//
//        int before = countRowsInTable("tb_resource");
//
//        accountService.saveResource(resource);
//
//        int after = countRowsInTable("tb_resource");
//
//        assertEquals(before + 1, after);
//        assertEquals(resource.get("id"), 23L);
//
//        resource = accountService.getResource(2L);
//
//        resource.put("value","modify value");
//
//        accountService.saveResource(resource);
//
//        resource = accountService.getResource(2L);
//        assertEquals(resource.get("value"), "modify value");
//
//    }
//
//    @Test
//    @SuppressWarnings("unchecked")
//    public void testMergeResources(){
//        List<Map<String, Object>> list = accountService.getResources();
//        List<Map<String, Object>> full = accountService.mergeResources(list);
//
//        ArrayList<Map<String, Object>> children = (ArrayList<Map<String, Object>>)full.get(0).get("children");
//        assertEquals(full.size(), 2);
//        assertEquals(children.size(), 3);
//
//        children = (ArrayList<Map<String, Object>>)children.get(0).get("children");
//        assertEquals(children.size(), 3);
//
//        list = accountService.getResources();
//        List<Map<String, Object>> filter = accountService.mergeResources(list, ResourceType.SECURITY);
//
//        children = (ArrayList<Map<String, Object>>)filter.get(0).get("children");
//        assertEquals(filter.size(), 2);
//        assertEquals(children.size(), 3);
//        children = (ArrayList<Map<String, Object>>)children.get(0).get("children");
//        assertEquals(children.size(),0);
//    }
//
//    @Test
//    public void testGetUserResources(){
//        List<Map<String,Object>> list = accountService.getUserResources(5L);
//        assertEquals(list.size(), 22);
//
//        list = accountService.getUserResources(99L);
//        assertEquals(list.size(), 0);
//    }
//
//    @Test
//    public void testGetGroupResources() {
//        List<Map<String,Object>> list = accountService.getGroupResources(3L);
//        assertEquals(list.size(), 22);
//
//        list = accountService.getGroupResources(99L);
//        assertEquals(list.size(), 0);
//    }
//}