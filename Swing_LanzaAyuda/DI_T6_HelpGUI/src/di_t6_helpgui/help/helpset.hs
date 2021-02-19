<?xml version='1.0' encoding='ISO−8859−1'?>
<!DOCTYPE helpset PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 2.0//EN" "http://java.sun.com/products/javahelp/helpset_2_0.dtd">
<helpset version="2.0">

    <title>Prueba de HelpSet!</title>

    <maps>
        <!-- Hace referencia a mi target Index -->
        <homeID>t_index</homeID>
        <mapref location="map.jhm" />
    </maps>

    <view xml:lang="en" mergetype="javax.help.UniteAppendMerge">
        <name>TOC</name>
        <label>Tabla de Contenidos</label>
        <type>javax.help.TOCView</type>
        <!-- Referenciamos en el campo data nuestro documento TOC (Tabla de Contenidos) -->
        <data>TOC.xml</data>
    </view>

    <view xml:lang="en" mergetype="javax.help.SortMerge">
        <name>Index</name>
        <label>Indice</label>
        <type>javax.help.IndexView</type>
        <data>index.html</data>
    </view>

    <!--
    <view xml:lang="en" mergetype="javax.help.SortMerge">
        <name>Resumen</name>
        <label>Resumen</label>
        <type>javax.help.SearchView</type>
        <data>resumenbreve.html</data>
    </view>

    <view xml:lang="en" mergetype="javax.help.SortMerge">
        <name>Menu</name>
        <label>Menu</label>
        <type>javax.help.SearchView</type>
        <data>menuprincipal.html</data>
    </view>

    <view xml:lang="en" mergetype="javax.help.SortMerge">
        <name>Navegacion Basica</name>
        <label>Navegacion Basica</label>
        <type>javax.help.SearchView</type>
        <data>navbasica.html</data>
    </view>

    <view xml:lang="en" mergetype="javax.help.SortMerge">
        <name>Started</name>
        <label>Started</label>
        <type>javax.help.SearchView</type>
        <data>getstarted.html</data>
    </view>

    <view xml:lang="en" mergetype="javax.help.SortMerge">
        <name>Reservas Habitaciones</name>
        <label>Reservas Habitaciones</label>
        <type>javax.help.SearchView</type>
        <data>reshabitaciones.html</data>
    </view>

    <view xml:lang="en" mergetype="javax.help.SortMerge">
        <name>Reservas Habana</name>
        <label>Reservas Habana</label>
        <type>javax.help.SearchView</type>
        <data>reshabitaciones.html</data>
    </view>
    -->
    <view xml:lang="en" mergetype="javax.help.SortMerge">
        <name>Ayudas</name>
        <label>Ayudas</label>
        <type></type>
        <data>ayudas.html</data>
    </view>

    <view xml:lang="en" mergetype="javax.help.SortMerge">
        <name>FAQ</name>
        <label>FAQ</label>
        <type></type>
        <data>faq.html</data>
    </view>    

</helpset>