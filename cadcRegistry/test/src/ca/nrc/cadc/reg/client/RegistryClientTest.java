/*
************************************************************************
*******************  CANADIAN ASTRONOMY DATA CENTRE  *******************
**************  CENTRE CANADIEN DE DONNÉES ASTRONOMIQUES  **************
*
*  (c) 2010.                            (c) 2010.
*  Government of Canada                 Gouvernement du Canada
*  National Research Council            Conseil national de recherches
*  Ottawa, Canada, K1A 0R6              Ottawa, Canada, K1A 0R6
*  All rights reserved                  Tous droits réservés
*
*  NRC disclaims any warranties,        Le CNRC dénie toute garantie
*  expressed, implied, or               énoncée, implicite ou légale,
*  statutory, of any kind with          de quelque nature que ce
*  respect to the software,             soit, concernant le logiciel,
*  including without limitation         y compris sans restriction
*  any warranty of merchantability      toute garantie de valeur
*  or fitness for a particular          marchande ou de pertinence
*  purpose. NRC shall not be            pour un usage particulier.
*  liable in any event for any          Le CNRC ne pourra en aucun cas
*  damages, whether direct or           être tenu responsable de tout
*  indirect, special or general,        dommage, direct ou indirect,
*  consequential or incidental,         particulier ou général,
*  arising from the use of the          accessoire ou fortuit, résultant
*  software.  Neither the name          de l'utilisation du logiciel. Ni
*  of the National Research             le nom du Conseil National de
*  Council of Canada nor the            Recherches du Canada ni les noms
*  names of its contributors may        de ses  participants ne peuvent
*  be used to endorse or promote        être utilisés pour approuver ou
*  products derived from this           promouvoir les produits dérivés
*  software without specific prior      de ce logiciel sans autorisation
*  written permission.                  préalable et particulière
*                                       par écrit.
*
*  This file is part of the             Ce fichier fait partie du projet
*  OpenCADC project.                    OpenCADC.
*
*  OpenCADC is free software:           OpenCADC est un logiciel libre ;
*  you can redistribute it and/or       vous pouvez le redistribuer ou le
*  modify it under the terms of         modifier suivant les termes de
*  the GNU Affero General Public        la “GNU Affero General Public
*  License as published by the          License” telle que publiée
*  Free Software Foundation,            par la Free Software Foundation
*  either version 3 of the              : soit la version 3 de cette
*  License, or (at your option)         licence, soit (à votre gré)
*  any later version.                   toute version ultérieure.
*
*  OpenCADC is distributed in the       OpenCADC est distribué
*  hope that it will be useful,         dans l’espoir qu’il vous
*  but WITHOUT ANY WARRANTY;            sera utile, mais SANS AUCUNE
*  without even the implied             GARANTIE : sans même la garantie
*  warranty of MERCHANTABILITY          implicite de COMMERCIALISABILITÉ
*  or FITNESS FOR A PARTICULAR          ni d’ADÉQUATION À UN OBJECTIF
*  PURPOSE.  See the GNU Affero         PARTICULIER. Consultez la Licence
*  General Public License for           Générale Publique GNU Affero
*  more details.                        pour plus de détails.
*
*  You should have received             Vous devriez avoir reçu une
*  a copy of the GNU Affero             copie de la Licence Générale
*  General Public License along         Publique GNU Affero avec
*  with OpenCADC.  If not, see          OpenCADC ; si ce n’est
*  <http://www.gnu.org/licenses/>.      pas le cas, consultez :
*                                       <http://www.gnu.org/licenses/>.
*
*  $Revision: 5 $
*
************************************************************************
*/

package ca.nrc.cadc.reg.client;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.nrc.cadc.auth.AuthMethod;
import ca.nrc.cadc.util.Log4jInit;

/**
 *
 * @author pdowler
 */
public class RegistryClientTest
{
private static Logger log = Logger.getLogger(RegistryClientTest.class);
    static
    {
        Log4jInit.setLevel("ca.nrc.cadc.reg", Level.INFO);
    }


    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
    }

    //static String GMS_URI = "ivo://cadc.nrc.ca/gms";
    //static String GMS_HTTP = "http://www.cadc-ccda.hia-iha.nrc-cnrc.gc.ca/gms";
    //static String GMS_HTTPS = "https://www.cadc-ccda.hia-iha.nrc-cnrc.gc.ca/gms";

    //static String VOS_URI = "ivo://cadc.nrc.ca/vospace";
    //static String VOS_HTTP = "http://www.canfar.phys.uvic.ca/vospace";
    //static String VOS_HTTPS = "https://www.canfar.phys.uvic.ca/vospace";

    static String DUMMY_URI = "ivo://example.com/srv";
    static String OTHER_URI = "ivo://example.com/bar";
    static String LONG_URI = "ivo://example.com/long";
    static String DUMMY_URL = "http://www.example.com/current/path/to/my/service";
    static String DUMMY_SURL = "https://www.example.com/current/path/to/my/service";
    static String DUMMY_CERT_URL = "https://www.example.com/current/path/to/my/x509-service";
    static String DUMMY_PASSWORD_URL = "http://www.example.com/current/path/to/my/auth-service";
    static String DUMMY_TOKEN_URL = DUMMY_URL;
    static String DUMMY_COOKIE_URL = DUMMY_URL;

    @Test
    public void testNotFound() throws Exception
    {
        try
        {
            RegistryClient rc = new RegistryClient();

            URL url = rc.getServiceURL(new URI("ivo://foo/bar"));
            Assert.assertNull(url);
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }

    @Test
    public void testFound() throws Exception
    {
        try
        {
            RegistryClient rc = new RegistryClient();

            URL expected = new URL(DUMMY_URL);
            URL url = rc.getServiceURL(new URI(DUMMY_URI));
            Assert.assertEquals(expected, url);
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
    @Test
    public void testFoundAltURL() throws Exception
    {
        try
        {
            // TODO: verify that this works with an arbitrary URL (eg http)
            RegistryClient rc = new RegistryClient(
                    RegistryClient.class.getClassLoader().getResource(
                        RegistryClient.class.getSimpleName() + ".properties") );

            URL expected = new URL(DUMMY_URL);
            URL url = rc.getServiceURL(new URI(DUMMY_URI));
            Assert.assertEquals(expected, url);
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }

    @Test
    public void testFoundViaConfigFile() throws Exception
    {
        String home = System.getProperty("user.home");
        try
        {
            String fakeHome = System.getProperty("user.dir") + "/test";
            log.debug("setting user.home = " + fakeHome);
            System.setProperty("user.home", fakeHome);
            RegistryClient rc = new RegistryClient();

            URL expected = new URL("http://alt.example.com/current/path/to/my/service");
            URL url = rc.getServiceURL(new URI("ivo://example.com/srv"), "http");
            Assert.assertEquals(expected, url);
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
        finally
        {
            // reset
            System.setProperty("user.home", home);
        }
    }


    @Test
    public void testFoundWithProtocol() throws Exception
    {
        try
        {
            RegistryClient rc = new RegistryClient();

            URL expected = new URL(DUMMY_URL);
            URL url = rc.getServiceURL(new URI(DUMMY_URI), "http");
            Assert.assertEquals(expected, url);

            expected = new URL(DUMMY_SURL);
            url = rc.getServiceURL(new URI(DUMMY_URI), "https");
            Assert.assertEquals(expected, url);

            expected = new URL(DUMMY_CERT_URL);
            url = rc.getServiceURL(new URI(DUMMY_URI), "https", null, AuthMethod.CERT);
            Assert.assertEquals(expected, url);
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }

    @Test
    public void testFoundWithAuthMethod() throws Exception
    {
        try
        {
            RegistryClient rc = new RegistryClient();

            URL expected = new URL(DUMMY_URL);
            URL url = rc.getServiceURL(new URI(DUMMY_URI), "http", null, AuthMethod.COOKIE);
            Assert.assertEquals(expected, url);

            url = rc.getServiceURL(new URI(DUMMY_URI), "http", null, AuthMethod.TOKEN);
            Assert.assertEquals(expected, url);

            url = rc.getServiceURL(new URI(DUMMY_URI), "http", null, AuthMethod.ANON);
            Assert.assertEquals(expected, url);

            expected = new URL(DUMMY_PASSWORD_URL);
            url = rc.getServiceURL(new URI(DUMMY_URI), "http", null, AuthMethod.PASSWORD);
            Assert.assertEquals(expected, url);

            expected = new URL(DUMMY_CERT_URL);
            url = rc.getServiceURL(new URI(DUMMY_URI), "https", null, AuthMethod.CERT);
            Assert.assertEquals(expected, url);

            url = rc.getServiceURL(new URI(DUMMY_URI), "http", null, AuthMethod.CERT);
            Assert.assertNull(url);

        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }

    @Test
    public void testAppendPath() throws Exception
    {
        try
        {
            RegistryClient rc = new RegistryClient();

            URL expected = new URL(DUMMY_URL + "/doit");
            URL url = rc.getServiceURL(new URI(DUMMY_URI), null, "/doit");
            Assert.assertEquals(expected, url);

            expected = new URL(DUMMY_CERT_URL + "/doit");
            url = rc.getServiceURL(new URI(DUMMY_URI), "https", "/doit", AuthMethod.CERT);
            Assert.assertEquals(expected, url);
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }

    @Test
    public void testModifyLocal() throws Exception
    {
        try
        {
            System.setProperty(RegistryClient.class.getName() + ".local", "true");
            RegistryClient rc = new RegistryClient();

            String localhost = InetAddress.getLocalHost().getCanonicalHostName();
            URL expected = new URL("http://" + localhost + "/current/path/to/my/service");

            URL url = rc.getServiceURL(new URI(DUMMY_URI));
            Assert.assertNotNull(url);
            Assert.assertEquals(expected, url);
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
        finally
        {
            System.setProperty(RegistryClient.class.getName() + ".local", "false");
        }
    }

    @Test
    public void testModifyHost() throws Exception
    {
        try
        {
            System.setProperty(RegistryClient.class.getName() + ".host", "foo.bar.com");
            RegistryClient rc = new RegistryClient();

            URL url = rc.getServiceURL(new URI(DUMMY_URI));
            Assert.assertNotNull(url);
            Assert.assertEquals("http://foo.bar.com/current/path/to/my/service", url.toExternalForm());

            url = rc.getServiceURL(new URI(DUMMY_URI), "https", null, AuthMethod.CERT);
            Assert.assertNotNull(url);
            Assert.assertEquals("https://foo.bar.com/current/path/to/my/x509-service", url.toExternalForm());
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
        finally
        {
            System.setProperty(RegistryClient.class.getName() + ".host", "");
        }
    }

    @Test
    public void testModifyShortHostname() throws Exception
    {
        try
        {
            System.setProperty(RegistryClient.class.getName() + ".shortHostname", "foo");
            RegistryClient rc = new RegistryClient();

            URL url = rc.getServiceURL(new URI(DUMMY_URI));
            Assert.assertNotNull(url);
            Assert.assertEquals("http://foo.example.com/current/path/to/my/service", url.toExternalForm());

        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
        finally
        {
            System.setProperty(RegistryClient.class.getName() + ".shortHostname", "");
        }
    }

    @Test
    public void testModifyShortHostnameLongDomain() throws Exception
    {
        try
        {
            System.setProperty(RegistryClient.class.getName() + ".shortHostname", "foo");
            RegistryClient rc = new RegistryClient();

            URL url = rc.getServiceURL(new URI(LONG_URI));
            log.info("long url: " + url);
            Assert.assertNotNull(url);
            Assert.assertEquals("http://foo.long.domain.example.net/current/path/to/my/service", url.toExternalForm());

        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
        finally
        {
            System.setProperty(RegistryClient.class.getName() + ".shortHostname", "");
        }
    }

    @Test
    public void testMatchDomain() throws Exception
    {
        try
        {
            System.setProperty(RegistryClient.class.getName() + ".shortHostname", "foo");
            System.setProperty(RegistryClient.class.getName() + ".domainMatch", "example.com,other.com");
            RegistryClient rc = new RegistryClient();

            URL url = rc.getServiceURL(new URI(DUMMY_URI));
            Assert.assertNotNull(url);
            Assert.assertEquals("http://foo.example.com/current/path/to/my/service", url.toExternalForm());

            // this one dopesn't match so hostname not modified
            URL other = rc.getServiceURL(new URI(OTHER_URI));
            Assert.assertNotNull(other);
            Assert.assertEquals("http://www.example.net/current/path/to/my/service", other.toExternalForm());

        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
        finally
        {
            System.setProperty(RegistryClient.class.getName() + ".shortHostname", "");
            System.setProperty(RegistryClient.class.getName() + ".domainMatch", "");
        }
    }
}
