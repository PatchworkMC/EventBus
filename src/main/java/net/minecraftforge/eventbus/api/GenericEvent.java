/*
 * Minecraft Forge
 * Copyright (c) 2016.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.minecraftforge.eventbus.api;

import java.lang.reflect.Type;

import net.minecraftforge.eventbus.ListenerList;

public class GenericEvent<T> extends Event implements IGenericEvent<T>
{
    private Class<T> type;
    protected GenericEvent(Class<T> type)
    {
        this.type = type;
    }

    @Override
    public Type getGenericType()
    {
        return type;
    }

    //Default things that are added by EventSubclassTransformer, but as we are excluded from transformers we must add ourselves.
    private static ListenerList LISTENER_LIST;
    public GenericEvent(){ }

    @Override
    protected void setup()
    {
        super.setup();
        if (LISTENER_LIST != null)
            return;
        LISTENER_LIST = new ListenerList(super.getListenerList());
    }

    @Override
    public ListenerList getListenerList()
    {
        return LISTENER_LIST;
    }
}
