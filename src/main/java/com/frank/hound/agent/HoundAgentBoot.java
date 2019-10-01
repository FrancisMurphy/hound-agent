package com.frank.hound.agent;

import com.alibaba.ttl.threadpool.agent.TtlAgent;
import com.frank.hound.agent.banner.BannerPrinter;

import java.lang.instrument.Instrumentation;

public class HoundAgentBoot
{
    public static void premain(String agentArgs, Instrumentation inst)
    {
        BannerPrinter.printBanner();
        TtlAgent.premain(agentArgs, inst);
    }

}
