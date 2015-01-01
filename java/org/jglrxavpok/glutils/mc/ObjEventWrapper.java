package org.jglrxavpok.glutils.mc;

import cpw.mods.fml.common.eventhandler.Event;

import org.jglrxavpok.glutils.ObjEvent;

public class ObjEventWrapper extends Event
{

    public ObjEvent objEvent;

    public ObjEventWrapper(ObjEvent e)
    {
        this.objEvent = e;
    }
    
    public boolean isCancelable()
    {
        return objEvent.canBeCancelled();
    }
}
