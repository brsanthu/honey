/*
 * [EntifyStrings.java]
 *
 * Summary: Inserts HTML entities such as &quot; into a String replacing the single character equivalents.
 *
 * Copyright: (c) 2005-2012 Roedy Green, Canadian Mind Products, http://mindprod.com
 *
 * Licence: This software may be copied and used freely for any purpose but military.
 *          http://mindprod.com/contact/nonmil.html
 *
 * Requires: JDK 1.5+
 *
 * Created with: JetBrains IntelliJ IDEA IDE http://www.jetbrains.com/idea/
 *
 * Version History:
 *  2.6 2009-04-05 StripEntities now leaves a space behind when it removes a <br><p><td> etc tag.
 *  2.7 2009-11-14 generate a table for the HTML cheat sheet of quote-like entities.
 *  2.8 2009-12-22 export table on HTML 5 entities. Now import csv file rather than embed entity facts.
 *  2.9 2010-01-29 export XHTML entities (currently same as HTML-4 entities).
 *  3.0 2011-01-05 remove deprecated methods. add toHTMLEntity, toXMLEntity, hide charToHTMLEntity, charToXMLEntity
 *  3.1 2011-02-10 rename methods to htmlEntify and xmlEntify
 */
package com.brsanthu.honey.handler;

/**
 * Inserts HTML entities such as &quot; into a String replacing the single character equivalents.
 * <p/>
 * Entities are coded inline in a giant case.
 *
 * @author Roedy Green, Canadian Mind Products
 * @version 3.1 2011-02-10 rename methods to entifyHTML and entifyXML
 * @see DeEntify
 * @see DeEntifyStrings
 * @see Entify
 * @see EntifyStrings
 * @see Flatten
 * @since 2005
 */
public class EntifyStrings
    {
    /**
     * /** should we generate hex entities in preference to decimal ones?
     */
    private static boolean preferHexEntities = true;

    // -------------------------- PUBLIC STATIC METHODS --------------------------

    /**
     * Converts text to HTML by quoting dangerous characters. Text must not already contain entities. e.g. " ==> &quot;
     * < ==> &lt; ordinary text passes unchanged. Does not convert space to &nbsp;
     *
     * @param text raw text to be processed. Must not be null.
     *
     * @return translated HTML text, or null if input is null.
     * @noinspection WeakerAccess
     */
    public static String entifyHTML( String text )
        {
        return entifyHTMLorXML( text, false );
        }

    /**
     * Converts text to XML by quoting dangerous characters.
     * Text must not already contain entities. e.g. " ==> &quot;
     * < ==> &lt; ordinary text passes unchanged. Does not convert space to &nbsp;
     *
     * @param text raw text to be processed. Must not be null.
     *
     * @return translated XML text, or null if input is null.
     * @noinspection WeakerAccess
     */
    public static String entifyXML( String text )
        {
        return entifyHTMLorXML( text, true );
        }

    
    
    
    /**
     * Can this character be included in an HTML document without entifying it?
     *
     * @param c character is question
     *
     * @return true if char is simple, false if char needs entity
     */
    public static boolean isSimple( char c )
        {
        return ' ' <= c && c <= '~' && !( c == '\"' || c == '&' || c == '<' || c == '>' );
        }

    /**
     * We should generate hex entities in preference to decimal ones.
     */
    public static void preferDecimalEntities()
        {
        preferHexEntities = false;
        }

    /**
     * We should generate decimal entities in preference to hex ones.
     */
    public static void preferHexEntities()
        {
        preferHexEntities = true;
        }

    /**
     * Converts text to HTML by quoting dangerous characters.;
     * Does not convert space to &nbsp;
     *
     * @param c raw character.
     *
     * @return translated HTML text, eg. & -> &amp  x -> x
     * @noinspection WeakerAccess
     */
    public static String toHTMLEntity( char c )
        {
        final String result = charToHTMLEntity( c );
        return result == null ? String.valueOf( c ) : result;
        }

    /**
     * Converts text to HTML by converting all characters to &#x9999; form
     *
     * @param c raw character.
     *
     * @return translated HTML text, eg. space --> &#x20;
     * @noinspection WeakerAccess
     */
    public static String toHexEntity( char c )
        {
        return "&#x" + Integer.toHexString( c );
        }

    /**
     * Converts text to XML by quoting dangerous characters.;
     * Does not convert space to &nbsp;
     *
     * @param c raw character.
     *
     * @return translated HTML text, eg. < -> &lt;  x -> x
     * @noinspection WeakerAccess
     */
    public static String toXMLEntity( char c )
        {
        final String result = charToXMLEntity( c );
        return result == null ? String.valueOf( c ) : result;
        }

    // -------------------------- STATIC METHODS --------------------------

    /**
     * convert a single char to its equivalent HTML entity. Ordinary chars are not changed. 160 -> &nbsp;  weird chars
     * -> &#nnn; form
     *
     * @param c Char to convert
     *
     * @return equivalent string e.g. &amp;, null means leave char as is.
     */
    private static String charToHTMLEntity( char c )
        {
        switch ( c )
            {
            default:
                if ( c < 127 )
                    {
                    // leave alone as equivalent string.
                    return null;
                    // faster than String.valueOf( c ).intern();
                    }
                else
                    {
                    if ( preferHexEntities )
                        {
                        // default, compact, no lead 0
                        return "&#x" + Integer.toHexString( c ) + ";";
                        }
                    else
                        {
                        //use the decimal &#nnn; form, compact, no lead 0.
                        return "&#" + Integer.toString( c ) + ";";
                        }
                    }
                // end default case

                // W A R N I N G !  _ M A N U A L L Y  _ I N S E R T E D _ G E N E R A T E D  _ C O D E
                // this code generated by Entities, include from com\mindprod\entities\entitiescase.javafrag
                // Could be more efficiently handled with a lookup table[9831]
                // manually sorted numerically.

            case 34:
                return "&quot;"     /* &#x22; quotation mark */;
            case 38:
                return "&amp;"      /* &#x26; ampersand */;
            case 60:
                return "&lt;"       /* &#x3c; less-than sign */;
            case 62:
                return "&gt;"       /* &#x3e; greater-than sign */;
            case 160:
                return "&nbsp;"     /* &#xa0; non-breaking space */;
            case 161:
                return "&iexcl;"    /* &#xa1; inverted exclamation mark */;
            case 162:
                return "&cent;"     /* &#xa2; cent sign */;
            case 163:
                return "&pound;"    /* &#xa3; pound sign */;
            case 164:
                return "&curren;"   /* &#xa4; currency sign */;
            case 165:
                return "&yen;"      /* &#xa5; yen sign */;
            case 166:
                return "&brvbar;"   /* &#xa6; broken bar */;
            case 167:
                return "&sect;"     /* &#xa7; section sign */;
            case 168:
                return "&uml;"      /* &#xa8; diaeresis */;
            case 169:
                return "&copy;"     /* &#xa9; copyright sign circled c */;
            case 170:
                return "&ordf;"     /* &#xaa; feminine ordinal indicator */;
            case 171:
                return "&laquo;"    /* &#xab; left guillemot */;
            case 172:
                return "&not;"      /* &#xac; not sign */;
            case 173:
                return "&shy;"      /* &#xad; soft hyphen */;
            case 174:
                return "&reg;"      /* &#xae; registered sign. circled R. */;
            case 175:
                return "&macr;"     /* &#xaf; macron */;
            case 176:
                return "&deg;"      /* &#xb0; degree sign */;
            case 177:
                return "&plusmn;"   /* &#xb1; plus-minus sign */;
            case 178:
                return "&sup2;"     /* &#xb2; superscript two */;
            case 179:
                return "&sup3;"     /* &#xb3; superscript three */;
            case 180:
                return "&acute;"    /* &#xb4; acute accent */;
            case 181:
                return "&micro;"    /* &#xb5; micro sign */;
            case 182:
                return "&para;"     /* &#xb6; pilcrow sign */;
            case 183:
                return "&middot;"   /* &#xb7; middle dot */;
            case 184:
                return "&cedil;"    /* &#xb8; cedilla */;
            case 185:
                return "&sup1;"     /* &#xb9; superscript one */;
            case 186:
                return "&ordm;"     /* &#xba; masculine ordinal indicator */;
            case 187:
                return "&raquo;"    /* &#xbb; right guillemot */;
            case 188:
                return "&frac14;"   /* &#xbc; vulgar fraction 1/4 */;
            case 189:
                return "&frac12;"   /* &#xbd; vulgar fraction 1/2 */;
            case 190:
                return "&frac34;"   /* &#xbe; vulgar fraction 3/4 */;
            case 191:
                return "&iquest;"   /* &#xbf; inverted question mark */;
            case 192:
                return "&Agrave;"   /* &#xc0; Latin capital letter A with grave */;
            case 193:
                return "&Aacute;"   /* &#xc1; Latin capital letter A with acute */;
            case 194:
                return "&Acirc;"    /* &#xc2; Latin capital letter A with circumflex */;
            case 195:
                return "&Atilde;"   /* &#xc3; Latin capital letter A with tilde */;
            case 196:
                return "&Auml;"     /* &#xc4; Latin capital letter A with diaeresis */;
            case 197:
                return "&Aring;"    /* &#xc5; Latin capital letter A with ring above */;
            case 198:
                return "&AElig;"    /* &#xc6; Latin capital letter AE */;
            case 199:
                return "&Ccedil;"   /* &#xc7; Latin capital letter C with cedilla */;
            case 200:
                return "&Egrave;"   /* &#xc8; Latin capital letter E with grave */;
            case 201:
                return "&Eacute;"   /* &#xc9; Latin capital letter E with acute */;
            case 202:
                return "&Ecirc;"    /* &#xca; Latin capital letter E with circumflex */;
            case 203:
                return "&Euml;"     /* &#xcb; Latin capital letter E with diaeresis */;
            case 204:
                return "&Igrave;"   /* &#xcc; Latin capital letter I with grave */;
            case 205:
                return "&Iacute;"   /* &#xcd; Latin capital letter I with acute */;
            case 206:
                return "&Icirc;"    /* &#xce; Latin capital letter I with circumflex */;
            case 207:
                return "&Iuml;"     /* &#xcf; Latin capital letter I with diaeresis */;
            case 208:
                return "&ETH;"      /* &#xd0; Latin capital letter Eth */;
            case 209:
                return "&Ntilde;"   /* &#xd1; Latin capital letter N with tilde */;
            case 210:
                return "&Ograve;"   /* &#xd2; Latin capital letter O with grave */;
            case 211:
                return "&Oacute;"   /* &#xd3; Latin capital letter O with acute */;
            case 212:
                return "&Ocirc;"    /* &#xd4; Latin capital letter O with circumflex */;
            case 213:
                return "&Otilde;"   /* &#xd5; Latin capital letter O with tilde */;
            case 214:
                return "&Ouml;"     /* &#xd6; Latin capital letter O with diaeresis */;
            case 215:
                return "&times;"    /* &#xd7; multiplication sign */;
            case 216:
                return "&Oslash;"   /* &#xd8; Latin capital letter O with stroke */;
            case 217:
                return "&Ugrave;"   /* &#xd9; Latin capital letter U with grave */;
            case 218:
                return "&Uacute;"   /* &#xda; Latin capital letter U with acute */;
            case 219:
                return "&Ucirc;"    /* &#xdb; Latin capital letter U with circumflex */;
            case 220:
                return "&Uuml;"     /* &#xdc; Latin capital letter U with diaeresis */;
            case 221:
                return "&Yacute;"   /* &#xdd; Latin capital letter Y with acute */;
            case 222:
                return "&THORN;"    /* &#xde; Latin capital letter Thorn */;
            case 223:
                return "&szlig;"    /* &#xdf; Latin small letter sharp s */;
            case 224:
                return "&agrave;"   /* &#xe0; Latin small letter a with grave */;
            case 225:
                return "&aacute;"   /* &#xe1; Latin small letter a with acute */;
            case 226:
                return "&acirc;"    /* &#xe2; Latin small letter a with circumflex */;
            case 227:
                return "&atilde;"   /* &#xe3; Latin small letter a with tilde */;
            case 228:
                return "&auml;"     /* &#xe4; Latin small letter a with diaeresis */;
            case 229:
                return "&aring;"    /* &#xe5; Latin small letter a with ring above */;
            case 230:
                return "&aelig;"    /* &#xe6; Latin lowercase ligature ae */;
            case 231:
                return "&ccedil;"   /* &#xe7; Latin small letter c with cedilla */;
            case 232:
                return "&egrave;"   /* &#xe8; Latin small letter e with grave */;
            case 233:
                return "&eacute;"   /* &#xe9; Latin small letter e with acute */;
            case 234:
                return "&ecirc;"    /* &#xea; Latin small letter e with circumflex */;
            case 235:
                return "&euml;"     /* &#xeb; Latin small letter e with diaeresis */;
            case 236:
                return "&igrave;"   /* &#xec; Latin small letter i with grave */;
            case 237:
                return "&iacute;"   /* &#xed; Latin small letter i with acute */;
            case 238:
                return "&icirc;"    /* &#xee; Latin small letter i with circumflex */;
            case 239:
                return "&iuml;"     /* &#xef; Latin small letter i with diaeresis */;
            case 240:
                return "&eth;"      /* &#xf0; Latin small letter eth */;
            case 241:
                return "&ntilde;"   /* &#xf1; Latin small letter n with tilde */;
            case 242:
                return "&ograve;"   /* &#xf2; Latin small letter o with grave */;
            case 243:
                return "&oacute;"   /* &#xf3; Latin small letter o with acute */;
            case 244:
                return "&ocirc;"    /* &#xf4; Latin small letter o with circumflex */;
            case 245:
                return "&otilde;"   /* &#xf5; Latin small letter o with tilde */;
            case 246:
                return "&ouml;"     /* &#xf6; Latin small letter o with diaeresis */;
            case 247:
                return "&divide;"   /* &#xf7; division sign */;
            case 248:
                return "&oslash;"   /* &#xf8; Latin small letter o with stroke */;
            case 249:
                return "&ugrave;"   /* &#xf9; Latin small letter u with grave */;
            case 250:
                return "&uacute;"   /* &#xfa; Latin small letter u with acute */;
            case 251:
                return "&ucirc;"    /* &#xfb; Latin small letter u with circumflex */;
            case 252:
                return "&uuml;"     /* &#xfc; Latin small letter u with diaeresis */;
            case 253:
                return "&yacute;"   /* &#xfd; Latin small letter y with acute */;
            case 254:
                return "&thorn;"    /* &#xfe; Latin small letter thorn */;
            case 255:
                return "&yuml;"     /* &#xff; Latin small letter y with diaeresis */;
            case 338:
                return "&OElig;"    /* &#x152; Latin capital ligature oe */;
            case 339:
                return "&oelig;"    /* &#x153; Latin small ligature oe */;
            case 352:
                return "&Scaron;"   /* &#x160; Latin capital letter S with caron */;
            case 353:
                return "&scaron;"   /* &#x161; Latin small letter s with caron */;
            case 376:
                return "&Yuml;"     /* &#x178; Latin capital letter Y with diaeresis */;
            case 402:
                return "&fnof;"     /* &#x192; Latin small letter f with hook */;
            case 710:
                return "&circ;"     /* &#x2c6; modifier letter circumflex accent */;
            case 732:
                return "&tilde;"    /* &#x2dc; small tilde */;
            case 913:
                return "&Alpha;"    /* &#x391; Greek capital letter Alpha */;
            case 914:
                return "&Beta;"     /* &#x392; Greek capital letter Beta */;
            case 915:
                return "&Gamma;"    /* &#x393; Greek capital letter Gamma */;
            case 916:
                return "&Delta;"    /* &#x394; Greek capital letter Delta */;
            case 917:
                return "&Epsilon;"  /* &#x395; Greek capital letter Epsilon */;
            case 918:
                return "&Zeta;"     /* &#x396; Greek capital letter Zeta */;
            case 919:
                return "&Eta;"      /* &#x397; Greek capital letter Eta */;
            case 920:
                return "&Theta;"    /* &#x398; Greek capital letter Theta */;
            case 921:
                return "&Iota;"     /* &#x399; Greek capital letter Iota */;
            case 922:
                return "&Kappa;"    /* &#x39a; Greek capital letter Kappa */;
            case 923:
                return "&Lambda;"   /* &#x39b; Greek capital letter Lambda */;
            case 924:
                return "&Mu;"       /* &#x39c; Greek capital letter Mu */;
            case 925:
                return "&Nu;"       /* &#x39d; Greek capital letter Nu */;
            case 926:
                return "&Xi;"       /* &#x39e; Greek capital letter Xi */;
            case 927:
                return "&Omicron;"  /* &#x39f; Greek capital letter Omicron */;
            case 928:
                return "&Pi;"       /* &#x3a0; Greek capital letter Pi */;
            case 929:
                return "&Rho;"      /* &#x3a1; Greek capital letter Rho */;
            case 931:
                return "&Sigma;"    /* &#x3a3; Greek capital letter Sigma */;
            case 932:
                return "&Tau;"      /* &#x3a4; Greek capital letter Tau */;
            case 933:
                return "&Upsilon;"  /* &#x3a5; Greek capital letter Upsilon */;
            case 934:
                return "&Phi;"      /* &#x3a6; Greek capital letter Phi */;
            case 935:
                return "&Chi;"      /* &#x3a7; Greek capital letter Chi */;
            case 936:
                return "&Psi;"      /* &#x3a8; Greek capital letter Psi */;
            case 937:
                return "&Omega;"    /* &#x3a9; Greek capital letter Omega */;
            case 945:
                return "&alpha;"    /* &#x3b1; Greek small letter alpha */;
            case 946:
                return "&beta;"     /* &#x3b2; Greek small letter beta */;
            case 947:
                return "&gamma;"    /* &#x3b3; Greek small letter gamma */;
            case 948:
                return "&delta;"    /* &#x3b4; Greek small letter delta */;
            case 949:
                return "&epsilon;"  /* &#x3b5; Greek small letter epsilon */;
            case 950:
                return "&zeta;"     /* &#x3b6; Greek small letter zeta */;
            case 951:
                return "&eta;"      /* &#x3b7; Greek small letter eta */;
            case 952:
                return "&theta;"    /* &#x3b8; Greek small letter theta */;
            case 953:
                return "&iota;"     /* &#x3b9; Greek small letter iota */;
            case 954:
                return "&kappa;"    /* &#x3ba; Greek small letter kappa */;
            case 955:
                return "&lambda;"   /* &#x3bb; Greek small letter lambda */;
            case 956:
                return "&mu;"       /* &#x3bc; Greek small letter mu */;
            case 957:
                return "&nu;"       /* &#x3bd; Greek small letter nu */;
            case 958:
                return "&xi;"       /* &#x3be; Greek small letter xi */;
            case 959:
                return "&omicron;"  /* &#x3bf; Greek small letter omicron */;
            case 960:
                return "&pi;"       /* &#x3c0; Greek small letter pi */;
            case 961:
                return "&rho;"      /* &#x3c1; Greek small letter rho */;
            case 962:
                return "&sigmaf;"   /* &#x3c2; Greek small letter final sigma */;
            case 963:
                return "&sigma;"    /* &#x3c3; Greek small letter sigma */;
            case 964:
                return "&tau;"      /* &#x3c4; Greek small letter tau */;
            case 965:
                return "&upsilon;"  /* &#x3c5; Greek small letter upsilon */;
            case 966:
                return "&phi;"      /* &#x3c6; Greek small letter phi */;
            case 967:
                return "&chi;"      /* &#x3c7; Greek small letter chi */;
            case 968:
                return "&psi;"      /* &#x3c8; Greek small letter psi */;
            case 969:
                return "&omega;"    /* &#x3c9; Greek small letter omega */;
            case 977:
                return "&thetasym;" /* &#x3d1; Greek theta symbol */;
            case 978:
                return "&upsih;"    /* &#x3d2; Greek upsilon with hook symbol */;
            case 982:
                return "&piv;"      /* &#x3d6; Greek pi symbol */;
            case 8194:
                return "&ensp;"     /* &#x2002; en space */;
            case 8195:
                return "&emsp;"     /* &#x2003; em space */;
            case 8201:
                return "&thinsp;"   /* &#x2009; thin space */;
            case 8204:
                return "&zwnj;"     /* &#x200c; zero width non-joiner */;
            case 8205:
                return "&zwj;"      /* &#x200d; zero width joiner */;
            case 8206:
                return "&lrm;"      /* &#x200e; left-to-right mark */;
            case 8207:
                return "&rlm;"      /* &#x200f; right-to-left mark */;
            case 8211:
                return "&ndash;"    /* &#x2013; en dash */;
            case 8212:
                return "&mdash;"    /* &#x2014; em dash */;
            case 8216:
                return "&lsquo;"    /* &#x2018; left single-6 quotation mark */;
            case 8217:
                return "&rsquo;"    /* &#x2019; right single-9 quotation mark */;
            case 8218:
                return "&sbquo;"    /* &#x201a; single low-9 quotation mark */;
            case 8220:
                return "&ldquo;"    /* &#x201c; left double-66 quotation mark */;
            case 8221:
                return "&rdquo;"    /* &#x201d; right double-99 quotation mark */;
            case 8222:
                return "&bdquo;"    /* &#x201e; double low-99 quotation mark */;
            case 8224:
                return "&dagger;"   /* &#x2020; dagger */;
            case 8225:
                return "&Dagger;"   /* &#x2021; double dagger */;
            case 8226:
                return "&bull;"     /* &#x2022; bullet */;
            case 8230:
                return "&hellip;"   /* &#x2026; horizontal ellipsis */;
            case 8240:
                return "&permil;"   /* &#x2030; per mille sign */;
            case 8242:
                return "&prime;"    /* &#x2032; prime */;
            case 8243:
                return "&Prime;"    /* &#x2033; double prime */;
            case 8249:
                return "&lsaquo;"   /* &#x2039; single left-pointing angle quotation mark */;
            case 8250:
                return "&rsaquo;"   /* &#x203a; single right-pointing angle quotation mark */;
            case 8254:
                return "&oline;"    /* &#x203e; overline */;
            case 8260:
                return "&frasl;"    /* &#x2044; fraction slash */;
            case 8364:
                return "&euro;"     /* &#x20ac; Euro currency sign */;
            case 8465:
                return "&image;"    /* &#x2111; black-letter capital i */;
            case 8472:
                return "&weierp;"   /* &#x2118; script capital p */;
            case 8476:
                return "&real;"     /* &#x211c; black-letter capital r */;
            case 8482:
                return "&trade;"    /* &#x2122; trademark sign */;
            case 8501:
                return "&alefsym;"  /* &#x2135; alef symbol */;
            case 8592:
                return "&larr;"     /* &#x2190; leftwards arrow */;
            case 8593:
                return "&uarr;"     /* &#x2191; upwards arrow */;
            case 8594:
                return "&rarr;"     /* &#x2192; rightwards arrow */;
            case 8595:
                return "&darr;"     /* &#x2193; downwards arrow */;
            case 8596:
                return "&harr;"     /* &#x2194; left right arrow */;
            case 8629:
                return "&crarr;"    /* &#x21b5; downwards arrow with corner leftwards */;
            case 8656:
                return "&lArr;"     /* &#x21d0; leftwards double arrow */;
            case 8657:
                return "&uArr;"     /* &#x21d1; upwards double arrow */;
            case 8658:
                return "&rArr;"     /* &#x21d2; rightwards double arrow */;
            case 8659:
                return "&dArr;"     /* &#x21d3; downwards double arrow */;
            case 8660:
                return "&hArr;"     /* &#x21d4; left right double arrow */;
            case 8704:
                return "&forall;"   /* &#x2200; for all */;
            case 8706:
                return "&part;"     /* &#x2202; partial differential */;
            case 8707:
                return "&exist;"    /* &#x2203; there exists */;
            case 8709:
                return "&empty;"    /* &#x2205; empty set */;
            case 8711:
                return "&nabla;"    /* &#x2207; nabla */;
            case 8712:
                return "&isin;"     /* &#x2208; element of */;
            case 8713:
                return "&notin;"    /* &#x2209; not an element of */;
            case 8715:
                return "&ni;"       /* &#x220b; like backwards epsilon */;
            case 8719:
                return "&prod;"     /* &#x220f; n-ary product */;
            case 8721:
                return "&sum;"      /* &#x2211; n-ary summation */;
            case 8722:
                return "&minus;"    /* &#x2212; minus sign */;
            case 8727:
                return "&lowast;"   /* &#x2217; asterisk operator */;
            case 8730:
                return "&radic;"    /* &#x221a; square root */;
            case 8733:
                return "&prop;"     /* &#x221d; proportional to */;
            case 8734:
                return "&infin;"    /* &#x221e; infinity */;
            case 8736:
                return "&ang;"      /* &#x2220; angle */;
            case 8743:
                return "&and;"      /* &#x2227; logical and */;
            case 8744:
                return "&or;"       /* &#x2228; vee */;
            case 8745:
                return "&cap;"      /* &#x2229; intersection */;
            case 8746:
                return "&cup;"      /* &#x222a; union */;
            case 8747:
                return "&int;"      /* &#x222b; integral */;
            case 8756:
                return "&there4;"   /* &#x2234; therefore three dots */;
            case 8764:
                return "&sim;"      /* &#x223c; tilde operator */;
            case 8773:
                return "&cong;"     /* &#x2245; congruent to */;
            case 8776:
                return "&asymp;"    /* &#x2248; asymptotic to */;
            case 8800:
                return "&ne;"       /* &#x2260; not equal to */;
            case 8801:
                return "&equiv;"    /* &#x2261; identical to */;
            case 8804:
                return "&le;"       /* &#x2264; less-than or equal to */;
            case 8805:
                return "&ge;"       /* &#x2265; greater-than or equal to */;
            case 8834:
                return "&sub;"      /* &#x2282; subset of */;
            case 8835:
                return "&sup;"      /* &#x2283; superset of */;
            case 8836:
                return "&nsub;"     /* &#x2284; not a subset of */;
            case 8838:
                return "&sube;"     /* &#x2286; subset of or equal to */;
            case 8839:
                return "&supe;"     /* &#x2287; superset of or equal to */;
            case 8853:
                return "&oplus;"    /* &#x2295; circled plus */;
            case 8855:
                return "&otimes;"   /* &#x2297; circled times */;
            case 8869:
                return "&perp;"     /* &#x22a5; up tack */;
            case 8901:
                return "&sdot;"     /* &#x22c5; dot operator */;
            case 8968:
                return "&lceil;"    /* &#x2308; left ceiling */;
            case 8969:
                return "&rceil;"    /* &#x2309; right ceiling */;
            case 8970:
                return "&lfloor;"   /* &#x230a; left floor */;
            case 8971:
                return "&rfloor;"   /* &#x230b; right floor */;
            case 9001:
                return "&lang;"     /* &#x2329; left-pointing angle bracket */;
            case 9002:
                return "&rang;"     /* &#x232a; right-pointing angle bracket */;
            case 9674:
                return "&loz;"      /* &#x25ca; open lozenge */;
            case 9824:
                return "&spades;"   /* &#x2660; black spade suit */;
            case 9827:
                return "&clubs;"    /* &#x2663; black club suit */;
            case 9829:
                return "&hearts;"   /* &#x2665; black heart suit */;
            case 9830:
                return "&diams;"    /* &#x2666; black diamond suit */;
            }// end switch
        // can't fall out bottom
        }// end charToEntity

    /**
     * convert a single char to its equivalent XML entity. Ordinary chars are not changed. 160 -> &nbsp;  weird chars
     * -> &#nnn; form
     *
     * @param c Char to convert
     *
     * @return equivalent string e.g. &amp;, null means leave char as is. Does not return unmodified letters.
     */
    private static String charToXMLEntity
    (
            char c )
        {
        switch ( c )
            {
            default:
                if ( c < 127 )
                    {
                    // leave alone as equivalent string.
                    return null;
                    // faster than String.valueOf( c ).intern();
                    }
                else
                    {
                    // use the &#nnn; form
                    return "&#" + Integer.toString( c ) + ";";
                    }
                // do NOT modify the following code. It is not generated.
            case 34:
                return "&quot;"/* &#x22; quotation mark */;
            case 38:
                return "&amp;"/* &#x26; ampersand */;
            // case 39:    // don't use apos, to make more compatible with HTML
            // return "&apos;"/* &#x27; apos */
            case 60:
                return "&lt;"/* &#x3c; less-than sign */;
            case 62:
                return "&gt;"/* &#x3e; greater-than sign */;
            case 160:
                return "&nbsp; /* &#x01; nbsp */;";
            }// end switch
        // can't fall out bottom
        }// end charToXMLEntity

    /**
     * Converts text to HTML by quoting dangerous characters. Text must not already contain entities. e.g. " ==> &quot;
     * < ==> &lt; ordinary text passes unchanged. Does not convert space to &nbsp;
     *
     * @param text     raw text to be processed. Must not be null.
     * @param xmlStyle true if insert basic four XML enities, otherwise use full HTML-4 set.
     *
     * @return translated text, or null if input is null.
     * @noinspection WeakerAccess
     */
    private static String entifyHTMLorXML( final String text, final boolean xmlStyle )
        {
        if ( text == null )
            {
            return null;
            }
        int originalTextLength = text.length();
        // estimate text will grow by no more than 10%
        StringBuilder sb = new StringBuilder( originalTextLength * 110 / 100 );
        int charsToAppend = 0;
        for ( int i = 0; i < originalTextLength; i++ )
            {
            char c = text.charAt( i );
            String entity = xmlStyle ? charToXMLEntity( c ) : charToHTMLEntity( c );
            if ( entity == null )
                {
                // we could sb.append( c ), but that would be slower
                // than saving them up for a big append.
                charsToAppend++;
                }
            else
                {
                if ( charsToAppend != 0 )
                    {
                    sb.append( text.substring( i - charsToAppend, i ) );
                    charsToAppend = 0;
                    }
                sb.append( entity );
                }
            }// end for
        // append chars to the right of the last entity.
        if ( charsToAppend != 0 )
            {
            sb.append( text.substring( originalTextLength - charsToAppend,
                    originalTextLength ) );
            }
        // if result is not longer, we did not do anything. Save RAM by returning the original
        return ( sb.length() == originalTextLength ) ? text : sb.toString();
        }

    //    /** test harness
    //     *
    //     * @param args  not used
    //     */
    //    public static void main ( String[] args )
    //    {
    //
    //    String s = "abc\"&xyz";
    //    System.out.println( entifyHTMLorXML( s , false  )) ;
    //    }
    }