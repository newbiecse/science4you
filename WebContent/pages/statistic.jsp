<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html lang="de">
   <!-- benutzt menuh.css, struktur.css und s4you.css und neue css-basierte Aufteilung -->
   <head>
      <title>
         naturbeobachtung.at : der Treffpunkt für Naturbeobachtung in Österreich
      </title>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta name="robots" content="nofollow">
      <meta name="revisit-after" content="15 days">
      <!-- Begin_Meta -->
      <!-- End_Meta -->
      <!-- Begin_Style -->
      <LINK rel="stylesheet" type="text/css" href="<html:rewrite page='/platform/common/styles/struktur.css'/>">      
      <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/platform/common/styles/s4you.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/platform/common/styles/global.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/platform/common/styles/taximage.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/platform/mo/nabeat/common/skins/styles/skin.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/platform/mo/nabeat/common/skins/styles/menu.css">
      <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/platform/mo/nabeat/common/skins/nabeat.favicon.ico">
      <!--[if lt IE 7]>
      <style type="text/css" media="screen">
         body{behavior:url(/platform/common/styles/csshover.htc); font-size:100%;}
      </style>
      <![endif]-->
      <!-- Colorbox default -->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/platform/common/scripts/colorbox/colorbox.css">
      <!-- End_Style -->
      <!-- Begin_Head -->
      <script language="JavaScript" src="${pageContext.request.contextPath}/platform/common/scripts/authortools.js"></script>
      <script language="JavaScript" src="${pageContext.request.contextPath}/platform/common/scripts/global.js"></script>
      <script language="JavaScript" src="${pageContext.request.contextPath}/platform/mo/nabeat/common/scripts/nabeat.js"></script>
      <!-- End_Head -->
      <!-- Begin_Head -->
      <!-- End_Head -->
      <!-- Begin_Script -->
      <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/platform/common/scripts/i8n.js"></script>
      <!--  
      <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/platform/common/scripts/jquery/jquery-1.8.2.min.js"></script>
      -->
      <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/platform/common/scripts/jquery/jquery-1.9.1.min.js"></script>
      <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/platform/common/scripts/jquery/jquery-ui-1.8.16.custom.min.js"></script>
      <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/platform/common/scripts/jquery/jquery.cookie.js"></script>
      <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/platform/common/scripts/jquery/jquery.hotkeys.js"></script>
      <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/platform/common/scripts/colorbox/jquery.colorbox-min.js"></script>      
      
      
      <script type="text/javascript">
         <!-- First_global_variables -->
         
         // sessionId für js verfügbar
         
         var globSid = "4EADFCD0B88D77575C8EFCD427DB929C"; 
         
         // credits:http://www.willmaster.com/
         // position for layers
         var cX = 0; var cY = 0; var rX = 0; var rY = 0;
         function UpdateCursorPosition(e){ cX = e.pageX; cY = e.pageY;}
         function UpdateCursorPositionDocAll(e){ cX = event.clientX; cY = event.clientY;}
         if(document.all) { document.onmousemove = UpdateCursorPositionDocAll; }
         else { document.onmousemove = UpdateCursorPosition; }
         
         
         //
         
         //<!-- Begin_Openlayers olload muss implementiert sein. -->
         
         function olload(){var ol=false;}
         
         
         <!-- End_Openlayers -->
         
         
         
      </script>			
      <!-- End_Script -->
      <script type="text/javascript"	src="${pageContext.request.contextPath}/platform/common/scripts/popup.js"></script>
      <script type="text/javascript">
         $(document).ready(function() {
         	// big pictures as overlays
         	$('.bigimage').colorbox({
         		next : 'nächstes',
         		close : 'schließen',
         		previous : 'voriges',
         		maxWidth : '100%',
         		maxHeight : '100%',
         		rel : 'bigimagegroup'
         	});
         	// Login-Overlay
         	$('a.login-window').click(function() {
         
         		//Getting the variable's value from a link 
         		var loginBox = $(this).attr('href');
         
         		//Fade in the Popup
         		$(loginBox).fadeIn(300);
         
         		//Set the center alignment padding + border see css style
         		var popMargTop = ($(loginBox).height() + 24) / 2;
         		var popMargLeft = ($(loginBox).width() + 24) / 2;
         
         		$(loginBox).css({
         			'margin-top' : -popMargTop,
         			'margin-left' : -popMargLeft
         		});
         
         		// Add the mask to body
         		$('body').append('<div id="mask"></div>');
         		$('#mask').fadeIn(300);
         
         		return false;
         	});
         
         	//When clicking on the button close or the mask layer the popup closed
         	//$('a.close, #mask').live('click', function() {
       		$('a.close, #mask').on('click', function() {
         		$('#mask , .login-popup').fadeOut(300, function() {
         			$('#mask').remove();
         		});
         		return false;
         	});
         });
         
         
         // Ajax vorbereiten - 29.9.15
         var xmlHttpObject = false;
         
         if (typeof XMLHttpRequest != 'undefined') {
         	xmlHttpObject = new XMLHttpRequest();
         }
         if (!xmlHttpObject) {
         	try {
         		xmlHttpObject = new ActiveXObject("Msxml2.XMLHTTP");
         	} catch (e) {
         		try {
         			xmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
         		} catch (e) {
         			xmlHttpObject = null;
         		}
         	}
         }
         
         
         function loadContentSync(url,id) {
         	xmlHttpObject.open('get', url, false);
         	xmlHttpObject.send();
         	document.getElementById(id).innerHTML = xmlHttpObject.responseText;
         	return false;
         }
         
         function loadContent(url,id) {
         	xmlHttpObject.open('get', url, true);
         	alert(url);
         	xmlHttpObject.onreadystatechange = handleContent(id);
         	xmlHttpObject.send();
         	return false;
         }
         
         function handleContent(id) {
         	if (xmlHttpObject.readyState == 4 && xmlhttp.status==200) {
         		var result = xmlHttpObject.responseText;
         		alert(result);
         		document.getElementById(id).innerHTML = result;
         	}
         }
         
         
      </script>
   </head>
   <body onload="olload();" >
      <script type="text/javascript">function showWait() {
         if (document.all) {
         	document.all.pageprogress.style.removeAttribute(
         			'visibility', 'false');
         } else {
         	document.getElementById('pageprogress').style.visibility = 'visible';
         }
         }
         // Popups simulieren
         function toggle(div_id) {
         var el = document.getElementById(div_id);
         if (el.style.display == 'none') {
         	el.style.display = 'block';
         } else {
         	el.style.display = 'none';
         }
         }
         
      </script>
      <div id="pageprogress">
         <img
            src="${pageContext.request.contextPath}/platform/common/images/indicators/loading-book.gif"
            align="middle" alt="">
         <p align="center"><b>Bitte warten ...</b></p>
      </div>
      <div id="blanket" style="display: none;"></div>
      <div id="popupDiv" style="display: none;"></div>
      <div id="popupDetailDiv" style="display: none;"></div>
      <div id="languageLayer" style="display: none;"></div>
      <div id="loginLayer" style="display: none;"></div>
      <div id="detailLayer" style="display: none;"></div>
      <div id="memberLayer" style="display: none;"></div>
      <div id="geocodeLayer" style="display: none;"></div>
      <div id="speciessheetLayer" style="display: none;"></div>
      <div id="qseditLayer" style="display: none;"></div>
      <div id="gathlistLayer" style="display: none;"></div>
      <!-- Overlay-Area 21.12.12 -->
      <div id="overlay" style="display: none;"></div>
      <div id="wrapper">
         <!-- Begin_header -->
         <div id="heading">
            <div id="pageheader">
               <div id="logo"><a
                  href="${pageContext.request.contextPath}/platform/mo/nabeat/index.do"
                  target="_top"><img src="${pageContext.request.contextPath}/platform/mo/nabeat/common/images/
                  nabeat_logo_2010_163x90.jpg"
                  alt="naturbeobachtung.at"
                  title="naturbeobachtung.at"
                  border="0"><br></a></div>
               <div id="nonlogo">
                  <div id="pathway"><a href="${pageContext.request.contextPath}/platform/mo/nabeat/home/index.do" target="_top" class="PathwayCurrent">naturbeobachtung.at</a></div>
                  <div id="sitemap"></div>
                  <div id="loggedinfo">
                     <p class="LoginPrompt">&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/platform/mo/nabeat/register/status.do?project=nabeat" class="login-window">Anmelden / Registrieren</a>
                     </p>
                  </div>
                  <div id="menuarea">
                     <div id="mainmenu">
                        <div class="menu">
                           <ul>
                              <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/home.do?doHome" target="_top" onclick="javascript:showWait()">Startseite</a></li>
                              <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/home.do?doDataHome" target="_top" onclick="javascript:showWait()">Beobachtung</a></li>
                              <li>
                                 <a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=artgroup&colclass=threeCol" target="_top" onclick="javascript:showWait()">Arten</a>
                                 <ul>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/species.do?doSpecSheetPage" target="_top" class="species2CSS" onclick="javascript:showWait()">Artsteckbriefe</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/species/maps.do" target="_top" class="mapsCSS" onclick="javascript:showWait()">Karten</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/species/maps/storedMaps.do?maptype=76" target="_top" class="mapsplateCSS" onclick="javascript:showWait()">Vergleichskarten</a></li>
                                 </ul>
                              </li>
                              <li>
                                 <a href="${pageContext.request.contextPath}/platform/mo/nabeat/statistics.do?doLastDays" target="_top" onclick="javascript:showWait()">Statistik</a>
                                 <ul>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/statistics.do?doLastDays&icc=AT" target="_top" class="currentCSS" onclick="javascript:showWait()">Neues</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/statistics/celebrities.do?doWatchlevel" target="_top" class="taxalistCSS" onclick="javascript:showWait()">Artenliste</a></li>
                                 </ul>
                              </li>
                              <li>
                                 <a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=imfokus&colclass=threeCol" target="_top" onclick="javascript:showWait()">Im Fokus</a>
                                 <ul>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/faltertage-at/home.do?doHome" target="_top" class="falterinwienCSS" onclick="javascript:showWait()">Abenteuer Faltertage</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/avesat/home.do?doHome" target="_top" class="avesatCSS" onclick="javascript:showWait()">Vögel Österreichs</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/bombus/home.do?doHome" target="_top" class="colatCSS" onclick="javascript:showWait()">Hummeln Österreichs</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/herpeto-at/home.do?doHome" target="_top" class="colatCSS" onclick="javascript:showWait()">Amphibien Österreichs</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/igelinwien/home.do?doHome" target="_top" class="igelinwienCSS" onclick="javascript:showWait()">Igel-in-Wien</a></li>
                                 </ul>
                              </li>
                              <li>
                                 <a href="${pageContext.request.contextPath}/platform/mo/nabeat/forum/threadList.do?tab=s2generalforum&id=441" target="_top" onclick="javascript:showWait()">Forum</a>
                                 <ul>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/forum/threadList.do?tab=s2generalforum&id=441" target="_top" class="forum2CSS" onclick="javascript:showWait()">Forum</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/forum/plate.do?tab=3758" target="_top" class="forumplateCSS" onclick="javascript:showWait()">Fototafel</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/forum/threadList.do?doForumList" target="_top" class="forum2CSS" onclick="javascript:showWait()">Forumsübersicht</a></li>
                                 </ul>
                              </li>
                              <li>
                                 <a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=imprint&colclass=threeCol" target="_top" onclick="javascript:showWait()">Info</a>
                                 <ul>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=naturschutzbund&colclass=threeCol" target="_top" class="naturschutzbundCSS" onclick="javascript:showWait()">Der Naturschutzbund</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=partner&colclass=threeCol" target="_top" class="partnerCSS" onclick="javascript:showWait()">Partner</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=sponsoren&colclass=threeCol" target="_top" class="sponsorenCSS" onclick="javascript:showWait()">Sponsoren</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=experten&colclass=threeCol" target="_top" class="expertenCSS" onclick="javascript:showWait()">Unsere Experten</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=presse&colclass=threeCol" target="_top" class="expertenCSS" onclick="javascript:showWait()">Presse</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=datarelease&colclass=threeCol" target="_top" class="expertenCSS" onclick="javascript:showWait()">Datenverwendung</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?doHelp" target="_top" class="helpCSS" onclick="javascript:showWait()">Hilfe</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?doLinks" target="_top" class="linksCSS" onclick="javascript:showWait()">Links</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?doHistory" target="_top" class="historyCSS" onclick="javascript:showWait()">Historie</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=imprint&colclass=threeCol" target="_top" class="imprintCSS" onclick="javascript:showWait()">Impressum</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=howto&colclass=threeCol" target="_top" class="qsjobsCSS" onclick="javascript:showWait()">Wie melde ich ?</a></li>
                                    <li><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?doFaq" target="_top" class="faqCSS" onclick="javascript:showWait()">FAQ</a></li>
                                 </ul>
                              </li>
                           </ul>
                        </div>
                     </div>
                     <div id="submenu"></div>
                  </div>
                  <div id="version"><span>naturbeobachtung.at V 2.09</span></div>
               </div>
            </div>
         </div>
         <!-- End_header --><!-- Begin_pagebase -->
         <div id="pagebase"
            class="twoCol">
            <!-- Begin_pagecontrol -->
            <div id="pagecontrol"
               class="twoCol">
               <p>&nbsp;</p>
               <p>Jahr wählen
               </p>
               <ul style="list-style-type:none;" >
                  <li>
                     <p><a href="${pageContext.request.contextPath}/platform/mo/nabeat/maps/storedMaps.do?maptype=76&amp;&amp;year=2000%2B" target="_self">2000+</a></p>
                  </li>
               </ul>
               <p>&nbsp;</p>
               
               <div id="formFilter">
               		<div id="speciesTree">
               		</div>
               </div>
               
               <p>&nbsp;</p>
               <p class="note">Diese Darstellung beruht auf periodischen, automatisiert erstellten Karten. Das Erstellungsdatum
                  ist bei der jeweiligen Karte vermerkt.
                  <br />Es werden hierzu alle freigegebenen Daten aus verschiedenen Projekten zusammengefasst. Bitte
                  beachten Sie, dass die Daten nur z.T. qualitätsgeprüft und fehlerbereinigt sind. Entsprechend
                  überprüfte Karten werden wir in Kürze auch online zur Verfügung stellen. Sie dürfen diese Daten für
                  eigenen Arbeiten und Publikationen verwenden, müssen aber darauf hinweisen, dass science4you und
                  seine Partner keine Gewähr für die Qualität der Daten übernehmen können. Als Quelle geben Sie bitte
                  www.science4you.org sowie das Datum an.
               </p>
            </div>
            <!-- End_pagecontrol --><!-- Begin_main -->
            <div id="main"
               class="twoCol">
               <!-- Begin_pageactions -->
               <div id="pageaction" class="twoCol">
               
               	  <a name="#top" id="top"></a>
               
                  <div id="speciesContainer">
                  </div>               
                  
               </div>
               <!-- End_pageactions -->
               <div id="content" class="twoCol">
               
                  <!-- Begin_content -->
                  <div class="StructureHint">
                     <p class="StructureHint">Tafel der Verbreitungskarten</p>
                  </div>
                  
				 <div class="specieslist">
				 	<table width="600px" border="0" cellpadding="1" cellspacing="1">
				 	
				 		<tr>
				 			<th bgcolor="#C0C4A6">
				 				<p>Gruppe /	Art</p>
				 			</th>
				 			<th bgcolor="#C0C4A6" align="center">
				 				<p>Minimumlevel</p>
				 			</th>
				 			<th bgcolor="#C0C4A6" align="center">
				 				<p>Sensitiv</p>
				 			</th>
				 			<th bgcolor="#C0C4A6" align="center">
				 				<p>Gruppe</p>
				 			</th>				 							 							 			
				 		</tr>
				 		
				 		<tr>
				 			<td colspan="5" align="right">
				 				<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#999386">
				 					<tr>
				 						<td><img src="/platform/common/images/layoutelements/onepixel.gif" width="1" height="1"></td>
				 					</tr>
				 				</table>
				 			</td>
				 		</tr>
				 		
				 		<tr>
				 			<td colspan="5">
				 				<p><b>Spechte</b></p>
				 			</td>
				 		</tr>
				 	</table>
				 </div>
				                     
                 <!-- End_content -->        
               </div>               
            </div>
            <!-- End_main -->
         </div>
         <!-- End_pagebase --><!-- Begin_footer -->
         <div id="footer" class="twoCol">
            <p align="center"><a href="#top" target="_self"><img
               src="${pageContext.request.contextPath}/platform/common/images/navigation/channelfooter.top.gif"
               alt="Nach oben" width="20" height="9" border="0"></a></p>
            <p class="ChannelFooter"><a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=imprint&colclass=threeCol" target="_top">Impressum</a>&nbsp;|&nbsp;
               <a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=disclaimer" target="_self">Haftungsausschluss</a>&nbsp;|&nbsp;
               <a href="${pageContext.request.contextPath}/platform/mo/nabeat/cmstext.do?pp=privacy" target="_self">Datenschutz</a>&nbsp;|&nbsp;Newsletter
            </p>
            <div id="logoFooter">
               <img src="${pageContext.request.contextPath}/platform/mo/nabeat/common/images/logo_oenb_2013_195x113.jpg" width="150" height="85"
                  alt="Naturschutzbund Österreich" title="Naturschutzbund Österreich" border="0">&nbsp;
               <img src="${pageContext.request.contextPath}/platform/common/images/logo/s4you_160x49.jpg" 
                  alt="science4you" title="science4you" border="0">
               <p>naturbeobachtung.at ist ein Kooperationsprojekt von <a href="http://www.naturschutzbund.at">|&nbsp;<b>naturschutzbund</b>&nbsp;|</a>&nbsp;und <a href="www.science4you.org">science4you</a></p>
            </div>
            <p onclick="showElements('admin')">&nbsp;&nbsp;.&nbsp;&nbsp;</p>
            <!-- Admin-Area -->
            <div id="admin">
               <p id="sid"></p>
               <button onclick="myFunction()">Get SID</button><script>function myFunction() {
                  document.getElementById("sid").innerHTML =
                  "SID: " + globSid;
                  }
               </script>
            </div>
         </div>
         <!-- End_footer -->
      </div>
      <!-- End_wrapper -->
            
   </body>
</html>