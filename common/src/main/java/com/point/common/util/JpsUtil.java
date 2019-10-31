package com.point.common.util;

import com.point.common.exception.DevelopmentException;
import sun.jvmstat.monitor.*;

import java.util.Set;

/**
 * sun.tools.jps.Jps的简单包装
 */
public class JpsUtil {

    /**
     * 构造函数
     */
    private JpsUtil() {
    }

    /**
     * 通过JVM参数获取PID
     * @param matcher 匹配方法
     * @return PID
     */
    public static Integer getProcessIdByVmArgs(VmArgsMather matcher) {

        if (matcher == null) {
            return null;
        }
        try {
            HostIdentifier hostId = new HostIdentifier("localhost");
            MonitoredHost host = MonitoredHost.getMonitoredHost(hostId);
            Set<Integer> vms = host.activeVms();
            for (Integer pid : vms) {
                VmIdentifier vmIdentifier = new VmIdentifier("//".concat(String.valueOf(pid)).concat("?mode=r"));
                MonitoredVm vm = host.getMonitoredVm(vmIdentifier, 0);
                String args = MonitoredVmUtil.jvmArgs(vm);
                if (matcher.isMatch(args)) {
                    return pid;
                }
            }
            return null;
        } catch (Throwable e) {
            throw new DevelopmentException(e);
        }
    }

    /**
     * 匹配接口
     */
    public interface VmArgsMather {

        /**
         * 是否匹配
         *
         * @param vmArgs JVM参数
         * @return 是否匹配
         */
        boolean isMatch(String vmArgs);
    }

    public static void main(String[] args) {
        getProcessIdByVmArgs((vmArgs) ->
                vmArgs != null && vmArgs.endsWith("9100")
        );
    }
}
