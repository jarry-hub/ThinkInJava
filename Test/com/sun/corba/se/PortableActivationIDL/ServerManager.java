package com.sun.corba.se.PortableActivationIDL;


/**
* com/sun/corba/se/PortableActivationIDL/ServerManager.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/com/sun/corba/se/PortableActivationIDL/activation.idl
* Friday, February 15, 2013 1:50:59 PM PST
*/


/** Interface used to combine the Activator and Locator when both are
    * implemented together in the same process, as is currently the case
    * for our implementation.
    */
public interface ServerManager extends ServerManagerOperations, com.sun.corba.se.PortableActivationIDL.Activator, com.sun.corba.se.PortableActivationIDL.Locator
{
} // interface ServerManager
