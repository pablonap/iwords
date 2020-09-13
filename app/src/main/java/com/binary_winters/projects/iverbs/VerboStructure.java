package com.binary_winters.projects.iverbs;

import com.binary_winters.projects.iverbs.modelo.VerboGenericoBean;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class VerboStructure {
    private Realm realm;
	private List<VerboEnEsp> listaDeVerbosEnEsp;
    private RealmResults<VerboEnEsp> verbosEnEspDeBD;
	
	private List<String> listaArise;
	private List<String> listaBe1;
	private List<String> listaBe2;
	private List<String> listaBeat;
	private List<String> listaBecome1;
	private List<String> listaBecome2;
	private List<String> listaBegin1;
	private List<String> listaBegin2;
	private List<String> listaBend;
	private List<String> listaBound;
	private List<String> listaBite;
	private List<String> listaBleed;
	private List<String> listaBlow1;
	private List<String> listaBlow2;
	private List<String> listaBreak1;
	private List<String> listaBreak2;
	private List<String> listaBreed;
	private List<String> listaBring;
	private List<String> listaBuild;
	private List<String> listaBurn;
	private List<String> listaBuy;
	private List<String> listaCast;
	private List<String> listaCatch;
	private List<String> listaCome1;
	private List<String> listaCome2;
	private List<String> listaCost;
	private List<String> listaCut;
	private List<String> listaChoose;
	private List<String> listaDeal;
	private List<String> listaDig;
	private List<String> listaDo;
	private List<String> listaDraw;
	private List<String> listaDream;
	private List<String> listaDrink1;
	private List<String> listaDrink2;
	private List<String> listaDrive;
	private List<String> listaEat;
	private List<String> listaFall;
	private List<String> listaFeed;
	private List<String> listaFeel;
	private List<String> listaFind;
	private List<String> listaFlee;
	private List<String> listaFly1;
	private List<String> listaFly2;
	private List<String> listaFly3;
	private List<String> listaForbid;
	private List<String> listaForget;
	private List<String> listaForgive;
	private List<String> listaGet;
	private List<String> listaGive;
	private List<String> listaGo1;
	private List<String> listaGo2;
	private List<String> listaGrow1;
	private List<String> listaGrow2;
	private List<String> listaHang1;
	private List<String> listaHang2;
	private List<String> listaHave1;
	private List<String> listaHave2;
	private List<String> listaHear1;
	private List<String> listaHear2;
	private List<String> listaHide1;
	private List<String> listaHide2;
	private List<String> listaHit1;
	private List<String> listaHit2;
	private List<String> listaHold1;
	private List<String> listaHold2;
	private List<String> listaHurt1;
	private List<String> listaHurt2;
	private List<String> listaKeep1;
	private List<String> listaKeep2;
	private List<String> listaKnow1;
	private List<String> listaKnow2;
	private List<String> listaKnow3;
	private List<String> listaKneel;
	private List<String> listaLay;
	private List<String> listaLead;
	private List<String> listaLean;
	private List<String> listaLeave;
	private List<String> listaLend;
	private List<String> listaLet;
	private List<String> listaLie;
	private List<String> listaLight;
	private List<String> listaLose;
	private List<String> listaMake1;
	private List<String> listaMake2;
	private List<String> listaMean;
	private List<String> listaMeet;
	private List<String> listaMistake1;
	private List<String> listaMistake2;
	private List<String> listaOvercome1;
	private List<String> listaOvercome2;
	private List<String> listaPay;
	private List<String> listaPut;
	private List<String> listaRead;
	private List<String> listaRide;
	private List<String> listaRing;
	private List<String> listaRise;
	private List<String> listaRun;
	private List<String> listaSay1;
	private List<String> listaSay2;
	private List<String> listaSee;
	private List<String> listaSell;
	private List<String> listaSend;
	private List<String> listaShake1;
	private List<String> listaShake2;
	private List<String> listaShine;
	private List<String> listaShoot;
	private List<String> listaShow1;
	private List<String> listaShow2;
	private List<String> listaShut;
	private List<String> listaSing;
	private List<String> listaSit;
	private List<String> listaSleep;
	private List<String> listaSlide;
	private List<String> listaSmell;
	private List<String> listaSow1;
	private List<String> listaSow2;
	private List<String> listaSpeak1;
	private List<String> listaSpeak2;
	private List<String> listaSpeed;
	private List<String> listaSpell;
	private List<String> listaSpend;
	private List<String> listaSpill;
	private List<String> listaSpit;
	private List<String> listaSpoil;
	private List<String> listaSpread;
	private List<String> listaSteal;
	private List<String> listaSting;
	private List<String> listaStink1;
	private List<String> listaStink2;
	private List<String> listaSwear1;
	private List<String> listaSwear2;
	private List<String> listaSweat1;
	private List<String> listaSweat2;
	private List<String> listaSweep1;
	private List<String> listaSweep2;
	private List<String> listaSwim1;
	private List<String> listaSwim2;
	private List<String> listaTake1;
	private List<String> listaTake2;
	private List<String> listaTeach;
	private List<String> listaTell;
	private List<String> listaThink1;
	private List<String> listaThink2;
	private List<String> listaThrow1;
	private List<String> listaThrow2;
	private List<String> listaUnderstand;
	private List<String> listaWake1;
	private List<String> listaWake2;
	private List<String> listaWake3;
	private List<String> listaWake4;
	private List<String> listaWake5;
	private List<String> listaWear1;
	private List<String> listaWear2;
	private List<String> listaWeep1;
	private List<String> listaWeep2;
	private List<String> listaWet1;
	private List<String> listaWet2;
	private List<String> listaWin1;
	private List<String> listaWin2;
	private List<String> listaWrite;
	
	public VerboStructure(){
		listaDeVerbosEnEsp = new ArrayList<VerboEnEsp>();
		
		listaArise = new ArrayList<String>();
		listaBe1 = new ArrayList<String>();
		listaBe2 = new ArrayList<String>();
		listaBeat = new ArrayList<String>();
		listaBecome1 = new ArrayList<String>();
		listaBecome2 = new ArrayList<String>();
		listaBegin1 = new ArrayList<String>();
		listaBegin2 = new ArrayList<String>();
		listaBend = new ArrayList<String>();
		listaBound = new ArrayList<String>();
		listaBite = new ArrayList<String>();
		listaBleed = new ArrayList<String>();
		listaBlow1 = new ArrayList<String>();
		listaBlow2 = new ArrayList<String>();
		listaBreak1 = new ArrayList<String>();
		listaBreak2 = new ArrayList<String>();
		listaBreed = new ArrayList<String>();
		listaBring = new ArrayList<String>();
		listaBuild = new ArrayList<String>();
		listaBurn = new ArrayList<String>();
		listaBuy = new ArrayList<String>();
		listaCast = new ArrayList<String>();
		listaCatch = new ArrayList<String>();
		listaCome1 = new ArrayList<String>();
		listaCome2 = new ArrayList<String>();
		listaCost = new ArrayList<String>();
		listaCut = new ArrayList<String>();
		listaChoose = new ArrayList<String>();
		listaDeal = new ArrayList<String>();
		listaDig = new ArrayList<String>();
		listaDo = new ArrayList<String>();
		listaDraw = new ArrayList<String>();
		listaDream = new ArrayList<String>();
		listaDrink1 = new ArrayList<String>();
		listaDrink2 = new ArrayList<String>();
		listaDrive = new ArrayList<String>();
		listaEat = new ArrayList<String>();
		listaFall = new ArrayList<String>();
		listaFeed = new ArrayList<String>();
		listaFeel = new ArrayList<String>();
		listaFind = new ArrayList<String>();
		listaFlee = new ArrayList<String>();
		listaFly1 = new ArrayList<String>();
		listaFly2 = new ArrayList<String>();
		listaFly3 = new ArrayList<String>();
		listaForbid = new ArrayList<String>();
		listaForget = new ArrayList<String>();
		listaForgive = new ArrayList<String>();
		listaGet = new ArrayList<String>();
		listaGive = new ArrayList<String>();
		listaGo1 = new ArrayList<String>();
		listaGo2 = new ArrayList<String>();
		listaGrow1 = new ArrayList<String>();
		listaGrow2 = new ArrayList<String>();
		listaHang1 = new ArrayList<String>();
		listaHang2 = new ArrayList<String>();
		listaHave1 = new ArrayList<String>();
		listaHave2 = new ArrayList<String>();
		listaHear1 = new ArrayList<String>();
		listaHear2 = new ArrayList<String>();
		listaHide1 = new ArrayList<String>();
		listaHide2 = new ArrayList<String>();
		listaHit1 = new ArrayList<String>();
		listaHit2 = new ArrayList<String>();
		listaHold1 = new ArrayList<String>();
		listaHold2 = new ArrayList<String>();
		listaHurt1 = new ArrayList<String>();
		listaHurt2 = new ArrayList<String>();
		listaKeep1 = new ArrayList<String>();
		listaKeep2 = new ArrayList<String>();
		listaKnow1 = new ArrayList<String>();
		listaKnow2 = new ArrayList<String>();
		listaKnow3 = new ArrayList<String>();
		listaKneel = new ArrayList<String>();
		listaLay = new ArrayList<String>();
		listaLead = new ArrayList<String>();
		listaLean = new ArrayList<String>();
		listaLeave = new ArrayList<String>();
		listaLend = new ArrayList<String>();
		listaLet = new ArrayList<String>();
		listaLie = new ArrayList<String>();
		listaLight = new ArrayList<String>();
		listaLose = new ArrayList<String>();
		listaMake1 = new ArrayList<String>();
		listaMake2 = new ArrayList<String>();
		listaMean = new ArrayList<String>();
		listaMeet = new ArrayList<String>();
		listaMistake1 = new ArrayList<String>();
		listaMistake2 = new ArrayList<String>();
		listaOvercome1 = new ArrayList<String>();
		listaOvercome2 = new ArrayList<String>();
		listaPay = new ArrayList<String>();
		listaPut = new ArrayList<String>();
		listaRead = new ArrayList<String>();
		listaRide = new ArrayList<String>();
		listaRing = new ArrayList<String>();
		listaRise = new ArrayList<String>();
		listaRun = new ArrayList<String>();
		listaSay1 = new ArrayList<String>();
		listaSay2 = new ArrayList<String>();
		listaSee = new ArrayList<String>();
		listaSell = new ArrayList<String>();
		listaSend = new ArrayList<String>();
		listaShake1 = new ArrayList<String>();
		listaShake2 = new ArrayList<String>();
		listaShine = new ArrayList<String>();
		listaShoot = new ArrayList<String>();
		listaShow1 = new ArrayList<String>();
		listaShow2 = new ArrayList<String>();
		listaShut = new ArrayList<String>();
		listaSing = new ArrayList<String>();
		listaSit = new ArrayList<String>();
		listaSleep = new ArrayList<String>();
		listaSlide = new ArrayList<String>();
		listaSmell = new ArrayList<String>();
		listaSow1 = new ArrayList<String>();
		listaSow2 = new ArrayList<String>();
		listaSpeak1 = new ArrayList<String>();
		listaSpeak2 = new ArrayList<String>();
		listaSpeed = new ArrayList<String>();
		listaSpell = new ArrayList<String>();
		listaSpend = new ArrayList<String>();
		listaSpill = new ArrayList<String>();
		listaSpit = new ArrayList<String>();
		listaSpoil = new ArrayList<String>();
		listaSpread = new ArrayList<String>();
		listaSteal = new ArrayList<String>();
		listaSting = new ArrayList<String>();
		listaStink1 = new ArrayList<String>();
		listaStink2 = new ArrayList<String>();
		listaSwear1 = new ArrayList<String>();
		listaSwear2 = new ArrayList<String>();
		listaSweat1 = new ArrayList<String>();
		listaSweat2 = new ArrayList<String>();
		listaSweep1 = new ArrayList<String>();
		listaSweep2 = new ArrayList<String>();
		listaSwim1 = new ArrayList<String>();
		listaSwim2 = new ArrayList<String>();
		listaTake1 = new ArrayList<String>();
		listaTake2 = new ArrayList<String>();
		listaTeach = new ArrayList<String>();
		listaTell = new ArrayList<String>();
		listaThink1 = new ArrayList<String>();
		listaThink2 = new ArrayList<String>();
		listaThrow1 = new ArrayList<String>();
		listaThrow2 = new ArrayList<String>();
		listaUnderstand = new ArrayList<String>();
		listaWake1 = new ArrayList<String>();
		listaWake2 = new ArrayList<String>();
		listaWake3 = new ArrayList<String>();
		listaWake4 = new ArrayList<String>();
		listaWake5 = new ArrayList<String>();
		listaWear1 = new ArrayList<String>();
		listaWear2 = new ArrayList<String>();
		listaWeep1 = new ArrayList<String>();
		listaWeep2 = new ArrayList<String>();
		listaWet1 = new ArrayList<String>();
		listaWet2 = new ArrayList<String>();
		listaWin1 = new ArrayList<String>();
		listaWin2 = new ArrayList<String>();
		listaWrite = new ArrayList<String>();

		listaArise.add("arise-arais");
		listaArise.add("arose-arous");
		listaArise.add("arisen-arisen");
		
		listaBe1.add("be-bi");
		listaBe1.add("was-uos");
		listaBe1.add("been-bin");

		listaBe2.add("be-bi");
		listaBe2.add("was-wos");
		listaBe2.add("been-bin");

		listaBeat.add("beat-bit");
		listaBeat.add("beat-bit");
		listaBeat.add("beaten-bitn");

		listaBecome1.add("become-bicam");
		listaBecome1.add("became-bikeim");
		listaBecome1.add("become-bicam");

		listaBecome2.add("become-bikam");
		listaBecome2.add("became-bikeim");
		listaBecome2.add("become-bikam");

		listaBegin1.add("begin-bigen");
		listaBegin1.add("began-bigan");
		listaBegin1.add("begun-bigan");

		listaBegin2.add("begin-biguen");
		listaBegin2.add("began-bigan");
		listaBegin2.add("begun-bigan");

		listaBend.add("bend-bend");
		listaBend.add("bent-bent");
		listaBend.add("bent-bent");

		listaBound.add("bind-baind");
		listaBound.add("bound-bount");
		listaBound.add("bound-bount");

		listaBite.add("bite-bait");
		listaBite.add("bit-bet");
		listaBite.add("bitten-betn");

		listaBleed.add("bleed-blid");
		listaBleed.add("bled-bled");
		listaBleed.add("bled-bled");

		listaBlow1.add("blow-blow");
		listaBlow1.add("blew-blu");
		listaBlow1.add("blown-blown");

		listaBlow2.add("blow-blou");
		listaBlow2.add("blew-blu");
		listaBlow2.add("blown-bloun");

		listaBreak1.add("break-breic");
		listaBreak1.add("broke-brouc");
		listaBreak1.add("broken-broucn");

		listaBreak2.add("break-breik");
		listaBreak2.add("broke-brouk");
		listaBreak2.add("broken-brouken");

		listaBreed.add("breed-brid");
		listaBreed.add("bred-bred");
		listaBreed.add("bred-bred");

		listaBring.add("bring-bring");
		listaBring.add("brought-brot");
		listaBring.add("brought-brot");

		listaBuild.add("build-bild");
		listaBuild.add("built-bilt");
		listaBuild.add("built-bilt");

		listaBurn.add("burn-born");
		listaBurn.add("burnt-bornt");
		listaBurn.add("burnt-bornt");

		listaBuy.add("buy-bai");
		listaBuy.add("bought-bot");
		listaBuy.add("bought-bot");

		listaCast.add("cast-cast");
		listaCast.add("cast-cast");
		listaCast.add("cast-cast");

		listaCatch.add("catch-catch");
		listaCatch.add("caught-cot");
		listaCatch.add("caught-cot");

		listaCome1.add("come-cam");
		listaCome1.add("came-keim");
		listaCome1.add("come-cam");

		listaCome2.add("come-kam");
		listaCome2.add("came-keim");
		listaCome2.add("come-kam");

		listaCost.add("cost-cost");
		listaCost.add("cost-cost");
		listaCost.add("cost-cost");

		listaCut.add("cut-cat");
		listaCut.add("cut-cat");
		listaCut.add("cut-cat");

		listaChoose.add("choose-chus");
		listaChoose.add("chose-chous");
		listaChoose.add("chosen-chousn");

		listaDeal.add("deal-dial");
		listaDeal.add("dealt-delt");
		listaDeal.add("dealt-delt");

		listaDig.add("dig-dig");
		listaDig.add("dug-dag");
		listaDig.add("dug-dag");

		listaDo.add("do-du");
		listaDo.add("did-did");
		listaDo.add("done-don");

		listaDraw.add("draw-dro");
		listaDraw.add("drew-dru");
		listaDraw.add("drawn-dron");

		listaDream.add("dream-drim");
		listaDream.add("dreamt-dremt");
		listaDream.add("dreamt-dremt");

		listaDrink1.add("drink-drink");
		listaDrink1.add("drank-drink");
		listaDrink1.add("drunk-dronk");

		listaDrink2.add("drink-drinc");
		listaDrink2.add("drank-drinc");
		listaDrink2.add("drunk-dronc");

		listaDrive.add("drive-draiv");
		listaDrive.add("drove-drouv");
		listaDrive.add("driven-drivn");

		listaEat.add("eat-it");
		listaEat.add("ate-eit");
		listaEat.add("eaten-itn");

		listaFall.add("fall-fol");
		listaFall.add("fell-fel");
		listaFall.add("fallen-folen");

		listaFeed.add("feed-fid");
		listaFeed.add("fed-fed");
		listaFeed.add("fed-fed");

		listaFeel.add("feel-fil");
		listaFeel.add("felt-felt");
		listaFeel.add("felt-felt");

		listaFind.add("find-faind");
		listaFind.add("found-found");
		listaFind.add("found-found");

		listaFlee.add("flee-fli");
		listaFlee.add("fled-fled");
		listaFlee.add("fled-fled");

		listaFly1.add("fly-flay");
		listaFly1.add("flew-flu");
		listaFly1.add("flawn-flown");

		listaFly2.add("fly-flai");
		listaFly2.add("flew-flu");
		listaFly2.add("flawn-flown");

		listaFly3.add("fly-flai");
		listaFly3.add("flew-flu");
		listaFly3.add("flawn-floun");

		listaForbid.add("forbid-forbed");
		listaForbid.add("forbade-forbeid");
		listaForbid.add("forbidden-forbedn");

		listaForget.add("forget-forget");
		listaForget.add("forgot-forgat");
		listaForget.add("forgotten-forgatn");

		listaForgive.add("forgive-forgiv");
		listaForgive.add("forgave-forgeiv");
		listaForgive.add("forgiven-forgevn");

		listaGet.add("get-get");
		listaGet.add("got-gat");
		listaGet.add("gotten-gatn");

		listaGive.add("give-giv");
		listaGive.add("gave-gev");
		listaGive.add("given-gevn");

		listaGo1.add("go-gou");
		listaGo1.add("went-uent");
		listaGo1.add("gone-gon");

		listaGo2.add("go-gow");
		listaGo2.add("went-went");
		listaGo2.add("gone-gon");

		listaGrow1.add("grow-grow");
		listaGrow1.add("grew-gru");
		listaGrow1.add("grown-grown");

		listaGrow2.add("grow-grou");
		listaGrow2.add("grew-gru");
		listaGrow2.add("grown-groun");

		listaHang1.add("hang-heng");
		listaHang1.add("hung-hong");
		listaHang1.add("hung-hong");

		listaHang2.add("hang-jeng");
		listaHang2.add("hung-jong");
		listaHang2.add("hung-jong");

		listaHave1.add("have-haf");
		listaHave1.add("had-had");
		listaHave1.add("had-had");

		listaHave2.add("have-jaf");
		listaHave2.add("had-jad");
		listaHave2.add("had-jad");

		listaHear1.add("hear-hir");
		listaHear1.add("heard-herd");
		listaHear1.add("heard-herd");

		listaHear2.add("hear-jir");
		listaHear2.add("heard-jerd");
		listaHear2.add("heard-jerd");

		listaHide1.add("hide-haid");
		listaHide1.add("hid-hed");
		listaHide1.add("hidden-hidn");

		listaHide2.add("hide-jaid");
		listaHide2.add("hid-jed");
		listaHide2.add("hidden-jidn");

		listaHit1.add("hit-hit");
		listaHit1.add("hit-hit");
		listaHit1.add("hit-hit");

		listaHit2.add("hit-jit");
		listaHit2.add("hit-jit");
		listaHit2.add("hit-jit");

		listaHold1.add("hold-hold");
		listaHold1.add("held-held");
		listaHold1.add("held-held");

		listaHold2.add("hold-jold");
		listaHold2.add("held-jeld");
		listaHold2.add("held-jeld");

		listaHurt1.add("hurt-hert");
		listaHurt1.add("hurt-hert");
		listaHurt1.add("hurt-hert");

		listaHurt2.add("hurt-jert");
		listaHurt2.add("hurt-jert");
		listaHurt2.add("hurt-jert");

		listaKeep1.add("keep-kip");
		listaKeep1.add("kept-kapt");
		listaKeep1.add("kept-kapt");

		listaKeep2.add("keep-kip");
		listaKeep2.add("kept-capt");
		listaKeep2.add("kept-capt");

		listaKnow1.add("know-nou");
		listaKnow1.add("knew-niu");
		listaKnow1.add("known-noun");

		listaKnow2.add("know-now");
		listaKnow2.add("knew-niu");
		listaKnow2.add("known-nown");

		listaKnow3.add("know-now");
		listaKnow3.add("knew-niw");
		listaKnow3.add("known-nown");

		listaKneel.add("kneel-nil");
		listaKneel.add("knelt-nelt");
		listaKneel.add("knelt-nelt");

		listaLay.add("lay-lei");
		listaLay.add("laid-leid");
		listaLay.add("laid-leid");

		listaLead.add("lead-lid");
		listaLead.add("led-led");
		listaLead.add("led-led");

		listaLean.add("lean-lin");
		listaLean.add("leant-lent");
		listaLean.add("leant-lent");

		listaLeave.add("leave-liv");
		listaLeave.add("left-left");
		listaLeave.add("left-left");

		listaLend.add("lend-lend");
		listaLend.add("lent-lent");
		listaLend.add("lent-lent");

		listaLet.add("let-let");
		listaLet.add("let-let");
		listaLet.add("let-let");

		listaLie.add("lie-lai");
		listaLie.add("lay-lei");
		listaLie.add("lain-lein");

		listaLight.add("light-lait");
		listaLight.add("lit-let");
		listaLight.add("lit-let");

		listaLose.add("lose-lus");
		listaLose.add("lost-lost");
		listaLose.add("lost-lost");

		listaMake1.add("make-meic");
		listaMake1.add("made-meid");
		listaMake1.add("made-meid");

		listaMake2.add("make-meik");
		listaMake2.add("made-meid");
		listaMake2.add("made-meid");

		listaMean.add("mean-min");
		listaMean.add("meant-ment");
		listaMean.add("meant-ment");

		listaMeet.add("meet-mit");
		listaMeet.add("met-me");
		listaMeet.add("met-me");

		listaMistake1.add("mistake-maisteik");
		listaMistake1.add("mistook-mistuk");
		listaMistake1.add("mistaken-masteikn");

		listaMistake2.add("mistake-masteic");
		listaMistake2.add("mistook-mistuc");
		listaMistake2.add("mistaken-masteicn");

		listaOvercome1.add("overcome-ouverkam");
		listaOvercome1.add("overcame-ouverkeim");
		listaOvercome1.add("overcome-ouverkam");

		listaOvercome2.add("overcome-ouvercam");
		listaOvercome2.add("overcame-ouverkeim");
		listaOvercome2.add("overcome-ouvercam");

		listaPay.add("pay-pei");
		listaPay.add("paid-peid");
		listaPay.add("paid-peid");

		listaPut.add("put-put");
		listaPut.add("put-put");
		listaPut.add("put-put");

		listaRead.add("read-rid");
		listaRead.add("read-red");
		listaRead.add("read-red");

		listaRide.add("ride-raid");
		listaRide.add("rode-roud");
		listaRide.add("ridden-ridn");

		listaRing.add("ring-ring");
		listaRing.add("rang-rang");
		listaRing.add("rung-ran");

		listaRise.add("rise-rais");
		listaRise.add("rose-rous");
		listaRise.add("risen-risn");

		listaRun.add("run-ran");
		listaRun.add("run-ran");
		listaRun.add("run-ran");

		listaSay1.add("say-sey");
		listaSay1.add("said-sed");
		listaSay1.add("said-sed");

		listaSay2.add("say-sei");
		listaSay2.add("said-sed");
		listaSay2.add("said-sed");

		listaSee.add("see-si");
		listaSee.add("saw-so");
		listaSee.add("seen-sin");

		listaSell.add("sell-sel");
		listaSell.add("sold-sold");
		listaSell.add("sold-sold");

		listaSend.add("send-send");
		listaSend.add("sent-sent");
		listaSend.add("sent-sent");

		listaShake1.add("shake-sheik");
		listaShake1.add("shook-shuk");
		listaShake1.add("shaken-sheikn");

		listaShake2.add("shake-sheic");
		listaShake2.add("shook-shuc");
		listaShake2.add("shaken-sheicn");

		listaShine.add("shine-shain");
		listaShine.add("shone-shoun");
		listaShine.add("shone-shoun");

		listaShoot.add("shoot-shut");
		listaShoot.add("shot-shot");
		listaShoot.add("shot-shot");

		listaShow1.add("show-show");
		listaShow1.add("showed-shoud");
		listaShow1.add("shown-shown");

		listaShow2.add("show-shou");
		listaShow2.add("showed-shoud");
		listaShow2.add("shown-shoun");

		listaShut.add("shut-shat");
		listaShut.add("shut-shat");
		listaShut.add("shut-shat");

		listaSing.add("sing-sin");
		listaSing.add("sang-sein");
		listaSing.add("sung-san");

		listaSit.add("sit-sit");
		listaSit.add("sat-sat");
		listaSit.add("sat-sat");

		listaSleep.add("sleep-slip");
		listaSleep.add("slept-slept");
		listaSleep.add("slept-slept");

		listaSlide.add("slide-slait");
		listaSlide.add("slid-slit");
		listaSlide.add("slid-slit");

		listaSmell.add("smell-smel");
		listaSmell.add("smelt-smelt");
		listaSmell.add("smelt-smelt");

		listaSow1.add("sow-sow");
		listaSow1.add("sowed-soud");
		listaSow1.add("sown-sown");

		listaSow2.add("sow-sou");
		listaSow2.add("sowed-soud");
		listaSow2.add("sown-soun");

		listaSpeak1.add("speak-spic");
		listaSpeak1.add("spoke-spouc");
		listaSpeak1.add("spoken-spouquen");

		listaSpeak2.add("speak-spik");
		listaSpeak2.add("spoke-spouk");
		listaSpeak2.add("spoken-spouken");

		listaSpeed.add("speed-spid");
		listaSpeed.add("sped-sped");
		listaSpeed.add("sped-sped");

		listaSpell.add("spell-spel");
		listaSpell.add("spelt-spelt");
		listaSpell.add("spelt-spelt");

		listaSpend.add("spend-spend");
		listaSpend.add("spent-spent");
		listaSpend.add("spent-spent");

		listaSpill.add("spill-spil");
		listaSpill.add("spilt-spilt");
		listaSpill.add("spilt-spilt");

		listaSpit.add("spit-spit");
		listaSpit.add("spat-spat");
		listaSpit.add("spat-spat");

		listaSpoil.add("spoil-spoil");
		listaSpoil.add("spoilt-spoilt");
		listaSpoil.add("spoilt-spoilt");

		listaSpread.add("spread-spred");
		listaSpread.add("spread-spred");
		listaSpread.add("spread-spred");

		listaSteal.add("steal-stial");
		listaSteal.add("stole-stol");
		listaSteal.add("stolen-stolen");

		listaSting.add("sting-sting");
		listaSting.add("stung-steng");
		listaSting.add("stung-stong");

		listaStink1.add("stink-stink");
		listaStink1.add("stank-steink");
		listaStink1.add("stunk-stonk");

		listaStink2.add("stink-stinc");
		listaStink2.add("stank-steinc");
		listaStink2.add("stunk-stonc");

		listaSwear1.add("swear-suer");
		listaSwear1.add("swore-suor");
		listaSwear1.add("sworn-suorn");

		listaSwear2.add("swear-swer");
		listaSwear2.add("swore-swor");
		listaSwear2.add("sworn-sworn");

		listaSweat1.add("sweat-suet");
		listaSweat1.add("sweat-suet");
		listaSweat1.add("sweat-suet");

		listaSweat2.add("sweat-swet");
		listaSweat2.add("sweat-swet");
		listaSweat2.add("sweat-swet");

		listaSweep1.add("sweep-suip");
		listaSweep1.add("swept-suept");
		listaSweep1.add("swept-suept");

		listaSweep2.add("sweep-swip");
		listaSweep2.add("swept-swept");
		listaSweep2.add("swept-swept");

		listaSwim1.add("swim-swim");
		listaSwim1.add("swam-sweam");
		listaSwim1.add("swum-swom");

		listaSwim2.add("swim-suim");
		listaSwim2.add("swam-sueam");
		listaSwim2.add("swum-suom");

		listaTake1.add("take-teic");
		listaTake1.add("took-tuc");
		listaTake1.add("taken-teicn");

		listaTake2.add("take-teik");
		listaTake2.add("took-tuk");
		listaTake2.add("taken-teikn");

		listaTeach.add("teach-tich");
		listaTeach.add("taught-tot");
		listaTeach.add("taught-tot");

		listaTell.add("tell-tel");
		listaTell.add("told-told");
		listaTell.add("told-told");

		listaThink1.add("think-zinc");
		listaThink1.add("thought-zot");
		listaThink1.add("thought-zot");

		listaThink2.add("think-zink");
		listaThink2.add("thought-zot");
		listaThink2.add("thought-zot");

		listaThrow1.add("throw-zrow");
		listaThrow1.add("threw-zru");
		listaThrow1.add("thrown-zrown");

		listaThrow2.add("throw-zrou");
		listaThrow2.add("threw-zru");
		listaThrow2.add("thrown-zroun");

		listaUnderstand.add("understand-anderstand");
		listaUnderstand.add("understood-anderstud");
		listaUnderstand.add("understood-anderstud");

		listaWake1.add("wake-ueic");
		listaWake1.add("woke-uouc");
		listaWake1.add("woken-uouquen");

		listaWake2.add("wake-ueik");
		listaWake2.add("woke-uouk");
		listaWake2.add("woken-uouken");

		listaWake3.add("wake-weic");
		listaWake3.add("woke-wouc");
		listaWake3.add("woken-wouquen");

		listaWake4.add("wake-weik");
		listaWake4.add("woke-wouk");
		listaWake4.add("woken-wouquen");

		listaWake5.add("wake-ueik");
		listaWake5.add("woke-wouk");
		listaWake5.add("woken-uowquen");

		listaWear1.add("wear-uear");
		listaWear1.add("wore-uor");
		listaWear1.add("worn-uorn");

		listaWear2.add("wear-wear");
		listaWear2.add("wore-wor");
		listaWear2.add("worn-worn");

		listaWeep1.add("weep-wip");
		listaWeep1.add("wept-wept");
		listaWeep1.add("wept-wept");

		listaWeep2.add("weep-uip");
		listaWeep2.add("wept-uept");
		listaWeep2.add("wept-uept");

		listaWet1.add("wet-wet");
		listaWet1.add("wet-wet");
		listaWet1.add("wet-wet");

		listaWet2.add("wet-uet");
		listaWet2.add("wet-uet");
		listaWet2.add("wet-uet");

		listaWin1.add("win-win");
		listaWin1.add("won-won");
		listaWin1.add("won-won");

		listaWin2.add("win-uin");
		listaWin2.add("won-uon");
		listaWin2.add("won-uon");

		listaWrite.add("write-rait");
		listaWrite.add("wrote-rout");
		listaWrite.add("written-ritn");

        realm = Realm.getDefaultInstance();
        verbosEnEspDeBD = realm.where(VerboEnEsp.class).findAll();

        // La primera vez que se ejecute la app en BD no hay ningún verbo en esp.
        if(verbosEnEspDeBD != null && verbosEnEspDeBD.isEmpty()){
            this.armarListaDeVerbosEnEsp();
            persistirPorPrimeraVez();
        } else{
			for(VerboEnEsp verboEsp :verbosEnEspDeBD){
				VerboEnEsp verboEnEsp = new VerboEnEsp(verboEsp.getNombreDeVerbo(),
						verboEsp.getVERBO_DESDE_APP(), verboEsp.getMarkedWithStar());
				verboEnEsp.setId(verboEsp.getId());
				listaDeVerbosEnEsp.add(verboEnEsp);
			}
        }
	}

	public void persistirPorPrimeraVez(){
        realm.beginTransaction();
        for(VerboEnEsp verbo : listaDeVerbosEnEsp){
			verbo.setIdFromBd();
            realm.copyToRealmOrUpdate(verbo);
        }
        realm.commitTransaction();
    }

	public void armarListaDeVerbosEnEsp(){
		VerboEnEsp verboEnEspSurgir = new VerboEnEsp(ValorDeVerbosEsp.SURGIR_LEVANTARSE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSer = new VerboEnEsp(ValorDeVerbosEsp.SER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspGolpearRepetidamente = new VerboEnEsp(ValorDeVerbosEsp.GOLPEAR_REPETIDAMENTE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspConvertirse = new VerboEnEsp(ValorDeVerbosEsp.CONVERTIRSE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspComenzar = new VerboEnEsp(ValorDeVerbosEsp.COMENZAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspDoblar = new VerboEnEsp(ValorDeVerbosEsp.DOBLAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspAtar = new VerboEnEsp(ValorDeVerbosEsp.ATAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspMorder = new VerboEnEsp(ValorDeVerbosEsp.MORDER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSangrar = new VerboEnEsp(ValorDeVerbosEsp.SANGRAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSoplar = new VerboEnEsp(ValorDeVerbosEsp.SOPLAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspRomper = new VerboEnEsp(ValorDeVerbosEsp.ROMPER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspCriar = new VerboEnEsp(ValorDeVerbosEsp.CRIAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspTraer = new VerboEnEsp(ValorDeVerbosEsp.TRAER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspConstruir = new VerboEnEsp(ValorDeVerbosEsp.CONSTRUIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspQuemar = new VerboEnEsp(ValorDeVerbosEsp.QUEMAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspComprar = new VerboEnEsp(ValorDeVerbosEsp.COMPRAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspArrojar = new VerboEnEsp(ValorDeVerbosEsp.ARROJAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspAtrapar = new VerboEnEsp(ValorDeVerbosEsp.ATRAPAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspVenir = new VerboEnEsp(ValorDeVerbosEsp.VENIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspCostar = new VerboEnEsp(ValorDeVerbosEsp.COSTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspCortar = new VerboEnEsp(ValorDeVerbosEsp.CORTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspElegir = new VerboEnEsp(ValorDeVerbosEsp.ELEGIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspNegociar = new VerboEnEsp(ValorDeVerbosEsp.NEGOCIAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspCavar = new VerboEnEsp(ValorDeVerbosEsp.CAVAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspHacerTareas = new VerboEnEsp(ValorDeVerbosEsp.HACER_TAREAS, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspDibujar = new VerboEnEsp(ValorDeVerbosEsp.DIBUJAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSoniar = new VerboEnEsp(ValorDeVerbosEsp.SONIAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspBeber = new VerboEnEsp(ValorDeVerbosEsp.BEBER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspConducir = new VerboEnEsp(ValorDeVerbosEsp.CONDUCIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspComer = new VerboEnEsp(ValorDeVerbosEsp.COMER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspCaer = new VerboEnEsp(ValorDeVerbosEsp.CAER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspAlimentar = new VerboEnEsp(ValorDeVerbosEsp.ALIMENTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSentir = new VerboEnEsp(ValorDeVerbosEsp.SENTIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspEncontrar = new VerboEnEsp(ValorDeVerbosEsp.ENCONTRAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspHuir = new VerboEnEsp(ValorDeVerbosEsp.HUIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspVolar = new VerboEnEsp(ValorDeVerbosEsp.VOLAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspProhibir = new VerboEnEsp(ValorDeVerbosEsp.PROHIBIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspOlvidar = new VerboEnEsp(ValorDeVerbosEsp.OLVIDAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspPerdonar = new VerboEnEsp(ValorDeVerbosEsp.PERDONAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspObtener = new VerboEnEsp(ValorDeVerbosEsp.OBTENER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspDar = new VerboEnEsp(ValorDeVerbosEsp.DAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspIr = new VerboEnEsp(ValorDeVerbosEsp.IR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspCrecer = new VerboEnEsp(ValorDeVerbosEsp.CRECER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspColgar = new VerboEnEsp(ValorDeVerbosEsp.COLGAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspTener = new VerboEnEsp(ValorDeVerbosEsp.TENER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspOir = new VerboEnEsp(ValorDeVerbosEsp.OIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspOcultar = new VerboEnEsp(ValorDeVerbosEsp.OCULTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspGolpear = new VerboEnEsp(ValorDeVerbosEsp.GOLPEAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSostener = new VerboEnEsp(ValorDeVerbosEsp.SOSTENER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspHerir = new VerboEnEsp(ValorDeVerbosEsp.HERIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspConservar = new VerboEnEsp(ValorDeVerbosEsp.CONSERVAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSaber = new VerboEnEsp(ValorDeVerbosEsp.SABER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspArrodillarse = new VerboEnEsp(ValorDeVerbosEsp.ARRODILLARSE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspPonerCuidadosamente = new VerboEnEsp(ValorDeVerbosEsp.PONER_CUIDADOSAMENTE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspLiderar = new VerboEnEsp(ValorDeVerbosEsp.LIDERAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspApoyarse = new VerboEnEsp(ValorDeVerbosEsp.APOYARSE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSalir = new VerboEnEsp(ValorDeVerbosEsp.SALIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspPrestar = new VerboEnEsp(ValorDeVerbosEsp.PRESTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspPermitir = new VerboEnEsp(ValorDeVerbosEsp.PERMITIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspMentir = new VerboEnEsp(ValorDeVerbosEsp.MENTIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspEncender = new VerboEnEsp(ValorDeVerbosEsp.ENCENDER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspPerder = new VerboEnEsp(ValorDeVerbosEsp.PERDER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspHacerOProducir = new VerboEnEsp(ValorDeVerbosEsp.HACER_PRODUCIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSignificar = new VerboEnEsp(ValorDeVerbosEsp.SIGNIFICAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspReunirse = new VerboEnEsp(ValorDeVerbosEsp.REUNIRSE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspEquivocarse = new VerboEnEsp(ValorDeVerbosEsp.EQUIVOCARSE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspVencer = new VerboEnEsp(ValorDeVerbosEsp.VENCER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspPagar = new VerboEnEsp(ValorDeVerbosEsp.PAGAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspPoner = new VerboEnEsp(ValorDeVerbosEsp.PONER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspLeer = new VerboEnEsp(ValorDeVerbosEsp.LEER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspMontar = new VerboEnEsp(ValorDeVerbosEsp.MONTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspLlamar = new VerboEnEsp(ValorDeVerbosEsp.LLAMAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSubir = new VerboEnEsp(ValorDeVerbosEsp.SUBIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspCorrer = new VerboEnEsp(ValorDeVerbosEsp.CORRER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspDecir = new VerboEnEsp(ValorDeVerbosEsp.DECIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspVer = new VerboEnEsp(ValorDeVerbosEsp.VER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspVender = new VerboEnEsp(ValorDeVerbosEsp.VENDER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspEnviar = new VerboEnEsp(ValorDeVerbosEsp.ENVIAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSacudir = new VerboEnEsp(ValorDeVerbosEsp.SACUDIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspBrillar = new VerboEnEsp(ValorDeVerbosEsp.BRILLAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspDisparar = new VerboEnEsp(ValorDeVerbosEsp.DISPARAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspMostrar = new VerboEnEsp(ValorDeVerbosEsp.MOSTRAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspCerrar = new VerboEnEsp(ValorDeVerbosEsp.CERRAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspCantar = new VerboEnEsp(ValorDeVerbosEsp.CANTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSentarse = new VerboEnEsp(ValorDeVerbosEsp.SENTARSE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspDormir = new VerboEnEsp(ValorDeVerbosEsp.DORMIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspResbalar = new VerboEnEsp(ValorDeVerbosEsp.RESBALAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspOler = new VerboEnEsp(ValorDeVerbosEsp.OLER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSembrar = new VerboEnEsp(ValorDeVerbosEsp.SEMBRAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspHablar = new VerboEnEsp(ValorDeVerbosEsp.HABLAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspAcelerar = new VerboEnEsp(ValorDeVerbosEsp.ACELERAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspDeletrear = new VerboEnEsp(ValorDeVerbosEsp.DELETREAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspGastar = new VerboEnEsp(ValorDeVerbosEsp.GASTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspDerramar = new VerboEnEsp(ValorDeVerbosEsp.DERRAMAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspEscupir = new VerboEnEsp(ValorDeVerbosEsp.ESCUPIR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspEstropear = new VerboEnEsp(ValorDeVerbosEsp.ESTROPEAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspExtender = new VerboEnEsp(ValorDeVerbosEsp.EXTENDER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspRobar = new VerboEnEsp(ValorDeVerbosEsp.ROBAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspPicar = new VerboEnEsp(ValorDeVerbosEsp.PICAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspApestar = new VerboEnEsp(ValorDeVerbosEsp.APESTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspJurar = new VerboEnEsp(ValorDeVerbosEsp.JURAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspSudar = new VerboEnEsp(ValorDeVerbosEsp.SUDAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspBarrer = new VerboEnEsp(ValorDeVerbosEsp.BARRER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspNadar = new VerboEnEsp(ValorDeVerbosEsp.NADAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspTomar = new VerboEnEsp(ValorDeVerbosEsp.TOMAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspEnseniar = new VerboEnEsp(ValorDeVerbosEsp.ENSENIAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspComentar = new VerboEnEsp(ValorDeVerbosEsp.COMENTAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspPensar = new VerboEnEsp(ValorDeVerbosEsp.PENSAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspLanzar = new VerboEnEsp(ValorDeVerbosEsp.LANZAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspEntender = new VerboEnEsp(ValorDeVerbosEsp.ENTENDER, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspDespertarse = new VerboEnEsp(ValorDeVerbosEsp.DESPERTARSE, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspLlevarPuesto = new VerboEnEsp(ValorDeVerbosEsp.LLEVAR_PUESTO, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspLlorar = new VerboEnEsp(ValorDeVerbosEsp.LLORAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspMojar = new VerboEnEsp(ValorDeVerbosEsp.MOJAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspGanar = new VerboEnEsp(ValorDeVerbosEsp.GANAR, Boolean.TRUE, Boolean.FALSE);
		VerboEnEsp verboEnEspEscribir = new VerboEnEsp(ValorDeVerbosEsp.ESCRIBIR, Boolean.TRUE, Boolean.FALSE);

		listaDeVerbosEnEsp.add(verboEnEspSurgir);
		listaDeVerbosEnEsp.add(verboEnEspSer);
		listaDeVerbosEnEsp.add(verboEnEspGolpearRepetidamente);
		listaDeVerbosEnEsp.add(verboEnEspConvertirse);
		listaDeVerbosEnEsp.add(verboEnEspComenzar);
		listaDeVerbosEnEsp.add(verboEnEspDoblar);
		listaDeVerbosEnEsp.add(verboEnEspAtar);
		listaDeVerbosEnEsp.add(verboEnEspMorder);
		listaDeVerbosEnEsp.add(verboEnEspSangrar);
		listaDeVerbosEnEsp.add(verboEnEspSoplar);
		listaDeVerbosEnEsp.add(verboEnEspRomper);
		listaDeVerbosEnEsp.add(verboEnEspCriar);
		listaDeVerbosEnEsp.add(verboEnEspTraer);
		listaDeVerbosEnEsp.add(verboEnEspConstruir);
		listaDeVerbosEnEsp.add(verboEnEspQuemar);
		listaDeVerbosEnEsp.add(verboEnEspComprar);
		listaDeVerbosEnEsp.add(verboEnEspArrojar);
		listaDeVerbosEnEsp.add(verboEnEspAtrapar);
		listaDeVerbosEnEsp.add(verboEnEspVenir);
		listaDeVerbosEnEsp.add(verboEnEspCostar);
		listaDeVerbosEnEsp.add(verboEnEspCortar);
		listaDeVerbosEnEsp.add(verboEnEspElegir);
		listaDeVerbosEnEsp.add(verboEnEspNegociar);
		listaDeVerbosEnEsp.add(verboEnEspCavar);
		listaDeVerbosEnEsp.add(verboEnEspHacerTareas);
		listaDeVerbosEnEsp.add(verboEnEspDibujar);
		listaDeVerbosEnEsp.add(verboEnEspSoniar);
		listaDeVerbosEnEsp.add(verboEnEspBeber);
		listaDeVerbosEnEsp.add(verboEnEspConducir);
		listaDeVerbosEnEsp.add(verboEnEspComer);
		listaDeVerbosEnEsp.add(verboEnEspCaer);
		listaDeVerbosEnEsp.add(verboEnEspAlimentar);
		listaDeVerbosEnEsp.add(verboEnEspSentir);
		listaDeVerbosEnEsp.add(verboEnEspEncontrar);
		listaDeVerbosEnEsp.add(verboEnEspHuir);
		listaDeVerbosEnEsp.add(verboEnEspVolar);
		listaDeVerbosEnEsp.add(verboEnEspProhibir);
		listaDeVerbosEnEsp.add(verboEnEspOlvidar);
		listaDeVerbosEnEsp.add(verboEnEspPerdonar);
		listaDeVerbosEnEsp.add(verboEnEspObtener);
		listaDeVerbosEnEsp.add(verboEnEspDar);
		listaDeVerbosEnEsp.add(verboEnEspIr);
		listaDeVerbosEnEsp.add(verboEnEspCrecer);
		listaDeVerbosEnEsp.add(verboEnEspColgar);
		listaDeVerbosEnEsp.add(verboEnEspTener);
		listaDeVerbosEnEsp.add(verboEnEspOir);
		listaDeVerbosEnEsp.add(verboEnEspOcultar);
		listaDeVerbosEnEsp.add(verboEnEspGolpear);
		listaDeVerbosEnEsp.add(verboEnEspSostener);
		listaDeVerbosEnEsp.add(verboEnEspHerir);
		listaDeVerbosEnEsp.add(verboEnEspConservar);
		listaDeVerbosEnEsp.add(verboEnEspSaber);
		listaDeVerbosEnEsp.add(verboEnEspArrodillarse);
		listaDeVerbosEnEsp.add(verboEnEspPonerCuidadosamente);
		listaDeVerbosEnEsp.add(verboEnEspLiderar);
		listaDeVerbosEnEsp.add(verboEnEspApoyarse);
		listaDeVerbosEnEsp.add(verboEnEspSalir);
		listaDeVerbosEnEsp.add(verboEnEspPrestar);
		listaDeVerbosEnEsp.add(verboEnEspPermitir);
		listaDeVerbosEnEsp.add(verboEnEspMentir);
		listaDeVerbosEnEsp.add(verboEnEspEncender);
		listaDeVerbosEnEsp.add(verboEnEspPerder);
		listaDeVerbosEnEsp.add(verboEnEspHacerOProducir);
		listaDeVerbosEnEsp.add(verboEnEspSignificar);
		listaDeVerbosEnEsp.add(verboEnEspReunirse);
		listaDeVerbosEnEsp.add(verboEnEspEquivocarse);
		listaDeVerbosEnEsp.add(verboEnEspVencer);
		listaDeVerbosEnEsp.add(verboEnEspPagar);
		listaDeVerbosEnEsp.add(verboEnEspPoner);
		listaDeVerbosEnEsp.add(verboEnEspLeer);
		listaDeVerbosEnEsp.add(verboEnEspMontar);
		listaDeVerbosEnEsp.add(verboEnEspLlamar);
		listaDeVerbosEnEsp.add(verboEnEspSubir);
		listaDeVerbosEnEsp.add(verboEnEspCorrer);
		listaDeVerbosEnEsp.add(verboEnEspDecir);
		listaDeVerbosEnEsp.add(verboEnEspVer);
		listaDeVerbosEnEsp.add(verboEnEspVender);
		listaDeVerbosEnEsp.add(verboEnEspEnviar);
		listaDeVerbosEnEsp.add(verboEnEspSacudir);
		listaDeVerbosEnEsp.add(verboEnEspBrillar);
		listaDeVerbosEnEsp.add(verboEnEspDisparar);
		listaDeVerbosEnEsp.add(verboEnEspMostrar);
		listaDeVerbosEnEsp.add(verboEnEspCerrar);
		listaDeVerbosEnEsp.add(verboEnEspCantar);
		listaDeVerbosEnEsp.add(verboEnEspSentarse);
		listaDeVerbosEnEsp.add(verboEnEspDormir);
		listaDeVerbosEnEsp.add(verboEnEspResbalar);
		listaDeVerbosEnEsp.add(verboEnEspOler);
		listaDeVerbosEnEsp.add(verboEnEspSembrar);
		listaDeVerbosEnEsp.add(verboEnEspHablar);
		listaDeVerbosEnEsp.add(verboEnEspAcelerar);
		listaDeVerbosEnEsp.add(verboEnEspDeletrear);
		listaDeVerbosEnEsp.add(verboEnEspGastar);
		listaDeVerbosEnEsp.add(verboEnEspDerramar);
		listaDeVerbosEnEsp.add(verboEnEspEscupir);
		listaDeVerbosEnEsp.add(verboEnEspEstropear);
		listaDeVerbosEnEsp.add(verboEnEspExtender);
		listaDeVerbosEnEsp.add(verboEnEspRobar);
		listaDeVerbosEnEsp.add(verboEnEspPicar);
		listaDeVerbosEnEsp.add(verboEnEspApestar);
		listaDeVerbosEnEsp.add(verboEnEspJurar);
		listaDeVerbosEnEsp.add(verboEnEspSudar);
		listaDeVerbosEnEsp.add(verboEnEspBarrer);
		listaDeVerbosEnEsp.add(verboEnEspNadar);
		listaDeVerbosEnEsp.add(verboEnEspTomar);
		listaDeVerbosEnEsp.add(verboEnEspEnseniar);
		listaDeVerbosEnEsp.add(verboEnEspComentar);
		listaDeVerbosEnEsp.add(verboEnEspPensar);
		listaDeVerbosEnEsp.add(verboEnEspLanzar);
		listaDeVerbosEnEsp.add(verboEnEspEntender);
		listaDeVerbosEnEsp.add(verboEnEspDespertarse);
		listaDeVerbosEnEsp.add(verboEnEspLlevarPuesto);
		listaDeVerbosEnEsp.add(verboEnEspLlorar);
		listaDeVerbosEnEsp.add(verboEnEspMojar);
		listaDeVerbosEnEsp.add(verboEnEspGanar);
		listaDeVerbosEnEsp.add(verboEnEspEscribir);
	}
	
	public List<VerboEnEsp> getListaDeVerbosEnEsp() {
		return listaDeVerbosEnEsp;
	}

	public void setlistaDeVerbosEnEsp(List<VerboEnEsp> listaDeVerbosEnEsp) {
		this.listaDeVerbosEnEsp = listaDeVerbosEnEsp;
	}

	public List<String> getListaArise() {
		return listaArise;
	}

	public void setListaArise(List<String> listaArise) {
		this.listaArise = listaArise;
	}

	public List<String> getListaBe1() {
		return listaBe1;
	}

	public void setListaBe1(List<String> listaBe1) {
		this.listaBe1 = listaBe1;
	}
	
	public List<String> getListaBe2() {
		return listaBe2;
	}

	public void setListaBe2(List<String> listaBe2) {
		this.listaBe2 = listaBe2;
	}

	public List<String> getListaBeat() {
		return listaBeat;
	}

	public void setListaBeat(List<String> listaBeat) {
		this.listaBeat = listaBeat;
	}

	public List<String> getListaBecome1() {
		return listaBecome1;
	}

	public void setListaBecome1(List<String> listaBecome1) {
		this.listaBecome1 = listaBecome1;
	}

	public List<String> getListaBecome2() {
		return listaBecome2;
	}

	public void setListaBecome2(List<String> listaBecome2) {
		this.listaBecome2 = listaBecome2;
	}

	public List<String> getListaBegin1() {
		return listaBegin1;
	}

	public void setListaBegin1(List<String> listaBegin1) {
		this.listaBegin1 = listaBegin1;
	}

	public List<String> getListaBegin2() {
		return listaBegin2;
	}

	public void setListaBegin2(List<String> listaBegin2) {
		this.listaBegin2 = listaBegin2;
	}

	public List<String> getListaBend() {
		return listaBend;
	}

	public void setListaBend(List<String> listaBend) {
		this.listaBend = listaBend;
	}

	public List<String> getListaBound() {
		return listaBound;
	}

	public void setListaBound(List<String> listaBound) {
		this.listaBound = listaBound;
	}

	public List<String> getListaBite() {
		return listaBite;
	}

	public void setListaBite(List<String> listaBite) {
		this.listaBite = listaBite;
	}

	public List<String> getListaBleed() {
		return listaBleed;
	}

	public void setListaBleed(List<String> listaBleed) {
		this.listaBleed = listaBleed;
	}

	public List<String> getListaBlow1() {
		return listaBlow1;
	}

	public void setListaBlow1(List<String> listaBlow1) {
		this.listaBlow1 = listaBlow1;
	}

	public List<String> getListaBlow2() {
		return listaBlow2;
	}

	public void setListaBlow2(List<String> listaBlow2) {
		this.listaBlow2 = listaBlow2;
	}

	public List<String> getListaBreak1() {
		return listaBreak1;
	}

	public void setListaBreak1(List<String> listaBreak1) {
		this.listaBreak1 = listaBreak1;
	}

	public List<String> getListaBreak2() {
		return listaBreak2;
	}

	public void setListaBreak2(List<String> listaBreak2) {
		this.listaBreak2 = listaBreak2;
	}

	public List<String> getListaBreed() {
		return listaBreed;
	}

	public void setListaBreed(List<String> listaBreed) {
		this.listaBreed = listaBreed;
	}

	public List<String> getListaBring() {
		return listaBring;
	}

	public void setListaBring(List<String> listaBring) {
		this.listaBring = listaBring;
	}

	public List<String> getListaBuild() {
		return listaBuild;
	}

	public void setListaBuild(List<String> listaBuild) {
		this.listaBuild = listaBuild;
	}

	public List<String> getListaBurn() {
		return listaBurn;
	}

	public void setListaBurn(List<String> listaBurn) {
		this.listaBurn = listaBurn;
	}

	public List<String> getListaBuy() {
		return listaBuy;
	}

	public void setListaBuy(List<String> listaBuy) {
		this.listaBuy = listaBuy;
	}

	public List<String> getListaCast() {
		return listaCast;
	}

	public void setListaCast(List<String> listaCast) {
		this.listaCast = listaCast;
	}

	public List<String> getListaCatch() {
		return listaCatch;
	}

	public void setListaCatch(List<String> listaCatch) {
		this.listaCatch = listaCatch;
	}

	public List<String> getListaCome1() {
		return listaCome1;
	}

	public void setListaCome1(List<String> listaCome1) {
		this.listaCome1 = listaCome1;
	}

	public List<String> getListaCome2() {
		return listaCome2;
	}

	public void setListaCome2(List<String> listaCome2) {
		this.listaCome2 = listaCome2;
	}

	public List<String> getListaCost() {
		return listaCost;
	}

	public void setListaCost(List<String> listaCost) {
		this.listaCost = listaCost;
	}

	public List<String> getListaCut() {
		return listaCut;
	}

	public void setListaCut(List<String> listaCut) {
		this.listaCut = listaCut;
	}

	public List<String> getListaChoose() {
		return listaChoose;
	}

	public void setListaChoose(List<String> listaChoose) {
		this.listaChoose = listaChoose;
	}

	public List<String> getListaDeal() {
		return listaDeal;
	}

	public void setListaDeal(List<String> listaDeal) {
		this.listaDeal = listaDeal;
	}

	public List<String> getListaDig() {
		return listaDig;
	}

	public void setListaDig(List<String> listaDig) {
		this.listaDig = listaDig;
	}

	public List<String> getListaDo() {
		return listaDo;
	}

	public void setListaDo(List<String> listaDo) {
		this.listaDo = listaDo;
	}

	public List<String> getListaDraw() {
		return listaDraw;
	}

	public void setListaDraw(List<String> listaDraw) {
		this.listaDraw = listaDraw;
	}

	public List<String> getListaDream() {
		return listaDream;
	}

	public void setListaDream(List<String> listaDream) {
		this.listaDream = listaDream;
	}

	public List<String> getListaDrink1() {
		return listaDrink1;
	}

	public void setListaDrink1(List<String> listaDrink1) {
		this.listaDrink1 = listaDrink1;
	}

	public List<String> getListaDrink2() {
		return listaDrink2;
	}

	public void setListaDrink2(List<String> listaDrink2) {
		this.listaDrink2 = listaDrink2;
	}

	public List<String> getListaDrive() {
		return listaDrive;
	}

	public void setListaDrive(List<String> listaDrive) {
		this.listaDrive = listaDrive;
	}

	public List<String> getListaEat() {
		return listaEat;
	}

	public void setListaEat(List<String> listaEat) {
		this.listaEat = listaEat;
	}

	public List<String> getListaFall() {
		return listaFall;
	}

	public void setListaFall(List<String> listaFall) {
		this.listaFall = listaFall;
	}

	public List<String> getListaFeed() {
		return listaFeed;
	}

	public void setListaFeed(List<String> listaFeed) {
		this.listaFeed = listaFeed;
	}

	public List<String> getListaFeel() {
		return listaFeel;
	}

	public void setListaFeel(List<String> listaFeel) {
		this.listaFeel = listaFeel;
	}

	public List<String> getListaFind() {
		return listaFind;
	}

	public void setListaFind(List<String> listaFind) {
		this.listaFind = listaFind;
	}

	public List<String> getListaFlee() {
		return listaFlee;
	}

	public void setListaFlee(List<String> listaFlee) {
		this.listaFlee = listaFlee;
	}

	public List<String> getListaFly1() {
		return listaFly1;
	}

	public void setListaFly1(List<String> listaFly1) {
		this.listaFly1 = listaFly1;
	}

	public List<String> getListaFly2() {
		return listaFly2;
	}

	public void setListaFly2(List<String> listaFly2) {
		this.listaFly2 = listaFly2;
	}

	public List<String> getListaFly3() {
		return listaFly3;
	}

	public void setListaFly3(List<String> listaFly3) {
		this.listaFly3 = listaFly3;
	}

	public List<String> getListaForbid() {
		return listaForbid;
	}

	public void setListaForbid(List<String> listaForbid) {
		this.listaForbid = listaForbid;
	}

	public List<String> getListaForget() {
		return listaForget;
	}

	public void setListaForget(List<String> listaForget) {
		this.listaForget = listaForget;
	}

	public List<String> getListaForgive() {
		return listaForgive;
	}

	public void setListaForgive(List<String> listaForgive) {
		this.listaForgive = listaForgive;
	}

	public List<String> getListaGet() {
		return listaGet;
	}

	public void setListaGet(List<String> listaGet) {
		this.listaGet = listaGet;
	}

	public List<String> getListaGive() {
		return listaGive;
	}

	public void setListaGive(List<String> listaGive) {
		this.listaGive = listaGive;
	}

	public List<String> getListaGo1() {
		return listaGo1;
	}

	public void setListaGo1(List<String> listaGo1) {
		this.listaGo1 = listaGo1;
	}

	public List<String> getListaGo2() {
		return listaGo2;
	}

	public void setListaGo2(List<String> listaGo2) {
		this.listaGo2 = listaGo2;
	}

	public List<String> getListaGrow1() {
		return listaGrow1;
	}

	public void setListaGrow1(List<String> listaGrow1) {
		this.listaGrow1 = listaGrow1;
	}

	public List<String> getListaGrow2() {
		return listaGrow2;
	}

	public void setListaGrow2(List<String> listaGrow2) {
		this.listaGrow2 = listaGrow2;
	}

	public List<String> getListaHang1() {
		return listaHang1;
	}

	public void setListaHang1(List<String> listaHang1) {
		this.listaHang1 = listaHang1;
	}

	public List<String> getListaHang2() {
		return listaHang2;
	}

	public void setListaHang2(List<String> listaHang2) {
		this.listaHang2 = listaHang2;
	}

	public List<String> getListaHave1() {
		return listaHave1;
	}

	public void setListaHave1(List<String> listaHave1) {
		this.listaHave1 = listaHave1;
	}

	public List<String> getListaHave2() {
		return listaHave2;
	}

	public void setListaHave2(List<String> listaHave2) {
		this.listaHave2 = listaHave2;
	}

	public List<String> getListaHear1() {
		return listaHear1;
	}

	public void setListaHear1(List<String> listaHear1) {
		this.listaHear1 = listaHear1;
	}

	public List<String> getListaHear2() {
		return listaHear2;
	}

	public void setListaHear2(List<String> listaHear2) {
		this.listaHear2 = listaHear2;
	}

	public List<String> getListaHide1() {
		return listaHide1;
	}

	public void setListaHide1(List<String> listaHide1) {
		this.listaHide1 = listaHide1;
	}

	public List<String> getListaHide2() {
		return listaHide2;
	}

	public void setListaHide2(List<String> listaHide2) {
		this.listaHide2 = listaHide2;
	}

	public List<String> getListaHit1() {
		return listaHit1;
	}

	public void setListaHit1(List<String> listaHit1) {
		this.listaHit1 = listaHit1;
	}

	public List<String> getListaHit2() {
		return listaHit2;
	}

	public void setListaHit2(List<String> listaHit2) {
		this.listaHit2 = listaHit2;
	}

	public List<String> getListaHold1() {
		return listaHold1;
	}

	public void setListaHold1(List<String> listaHold1) {
		this.listaHold1 = listaHold1;
	}

	public List<String> getListaHold2() {
		return listaHold2;
	}

	public void setListaHold2(List<String> listaHold2) {
		this.listaHold2 = listaHold2;
	}

	public List<String> getListaHurt1() {
		return listaHurt1;
	}

	public void setListaHurt1(List<String> listaHurt1) {
		this.listaHurt1 = listaHurt1;
	}

	public List<String> getListaHurt2() {
		return listaHurt2;
	}

	public void setListaHurt2(List<String> listaHurt2) {
		this.listaHurt2 = listaHurt2;
	}

	public List<String> getListaKeep1() {
		return listaKeep1;
	}

	public void setListaKeep1(List<String> listaKeep1) {
		this.listaKeep1 = listaKeep1;
	}

	public List<String> getListaKeep2() {
		return listaKeep2;
	}

	public void setListaKeep2(List<String> listaKeep2) {
		this.listaKeep2 = listaKeep2;
	}

	public List<String> getListaKnow1() {
		return listaKnow1;
	}

	public void setListaKnow1(List<String> listaKnow1) {
		this.listaKnow1 = listaKnow1;
	}

	public List<String> getListaKnow2() {
		return listaKnow2;
	}

	public void setListaKnow2(List<String> listaKnow2) {
		this.listaKnow2 = listaKnow2;
	}

	public List<String> getListaKnow3() {
		return listaKnow3;
	}

	public void setListaKnow3(List<String> listaKnow3) {
		this.listaKnow3 = listaKnow3;
	}

	public List<String> getListaKneel() {
		return listaKneel;
	}

	public void setListaKneel(List<String> listaKneel) {
		this.listaKneel = listaKneel;
	}

	public List<String> getListaLay() {
		return listaLay;
	}

	public void setListaLay(List<String> listaLay) {
		this.listaLay = listaLay;
	}

	public List<String> getListaLead() {
		return listaLead;
	}

	public void setListaLead(List<String> listaLead) {
		this.listaLead = listaLead;
	}

	public List<String> getListaLean() {
		return listaLean;
	}

	public void setListaLean(List<String> listaLean) {
		this.listaLean = listaLean;
	}

	public List<String> getListaLeave() {
		return listaLeave;
	}

	public void setListaLeave(List<String> listaLeave) {
		this.listaLeave = listaLeave;
	}

	public List<String> getListaLend() {
		return listaLend;
	}

	public void setListaLend(List<String> listaLend) {
		this.listaLend = listaLend;
	}

	public List<String> getListaLet() {
		return listaLet;
	}

	public void setListaLet(List<String> listaLet) {
		this.listaLet = listaLet;
	}

	public List<String> getListaLie() {
		return listaLie;
	}

	public void setListaLie(List<String> listaLie) {
		this.listaLie = listaLie;
	}

	public List<String> getListaLight() {
		return listaLight;
	}

	public void setListaLight(List<String> listaLight) {
		this.listaLight = listaLight;
	}

	public List<String> getListaLose() {
		return listaLose;
	}

	public void setListaLose(List<String> listaLose) {
		this.listaLose = listaLose;
	}

	public List<String> getListaMake1() {
		return listaMake1;
	}

	public void setListaMake1(List<String> listaMake1) {
		this.listaMake1 = listaMake1;
	}

	public List<String> getListaMake2() {
		return listaMake2;
	}

	public void setListaMake2(List<String> listaMake2) {
		this.listaMake2 = listaMake2;
	}

	public List<String> getListaMean() {
		return listaMean;
	}

	public void setListaMean(List<String> listaMean) {
		this.listaMean = listaMean;
	}

	public List<String> getListaMeet() {
		return listaMeet;
	}

	public void setListaMeet(List<String> listaMeet) {
		this.listaMeet = listaMeet;
	}

	public List<String> getListaMistake1() {
		return listaMistake1;
	}

	public void setListaMistake1(List<String> listaMistake1) {
		this.listaMistake1 = listaMistake1;
	}

	public List<String> getListaMistake2() {
		return listaMistake2;
	}

	public void setListaMistake2(List<String> listaMistake2) {
		this.listaMistake2 = listaMistake2;
	}

	public List<String> getListaOvercome1() {
		return listaOvercome1;
	}

	public void setListaOvercome1(List<String> listaOvercome1) {
		this.listaOvercome1 = listaOvercome1;
	}

	public List<String> getListaOvercome2() {
		return listaOvercome2;
	}

	public void setListaOvercome2(List<String> listaOvercome2) {
		this.listaOvercome2 = listaOvercome2;
	}

	public List<String> getListaPay() {
		return listaPay;
	}

	public void setListaPay(List<String> listaPay) {
		this.listaPay = listaPay;
	}

	public List<String> getListaPut() {
		return listaPut;
	}

	public void setListaPut(List<String> listaPut) {
		this.listaPut = listaPut;
	}

	public List<String> getListaRead() {
		return listaRead;
	}

	public void setListaRead(List<String> listaRead) {
		this.listaRead = listaRead;
	}

	public List<String> getListaRide() {
		return listaRide;
	}

	public void setListaRide(List<String> listaRide) {
		this.listaRide = listaRide;
	}

	public List<String> getListaRing() {
		return listaRing;
	}

	public void setListaRing(List<String> listaRing) {
		this.listaRing = listaRing;
	}

	public List<String> getListaRise() {
		return listaRise;
	}

	public void setListaRise(List<String> listaRise) {
		this.listaRise = listaRise;
	}

	public List<String> getListaRun() {
		return listaRun;
	}

	public void setListaRun(List<String> listaRun) {
		this.listaRun = listaRun;
	}

	public List<String> getListaSay1() {
		return listaSay1;
	}

	public void setListaSay1(List<String> listaSay1) {
		this.listaSay1 = listaSay1;
	}

	public List<String> getListaSay2() {
		return listaSay2;
	}

	public void setListaSay2(List<String> listaSay2) {
		this.listaSay2 = listaSay2;
	}

	public List<String> getListaSee() {
		return listaSee;
	}

	public void setListaSee(List<String> listaSee) {
		this.listaSee = listaSee;
	}

	public List<String> getListaSell() {
		return listaSell;
	}

	public void setListaSell(List<String> listaSell) {
		this.listaSell = listaSell;
	}

	public List<String> getListaSend() {
		return listaSend;
	}

	public void setListaSend(List<String> listaSend) {
		this.listaSend = listaSend;
	}

	public List<String> getListaShake1() {
		return listaShake1;
	}

	public void setListaShake1(List<String> listaShake1) {
		this.listaShake1 = listaShake1;
	}

	public List<String> getListaShake2() {
		return listaShake2;
	}

	public void setListaShake2(List<String> listaShake2) {
		this.listaShake2 = listaShake2;
	}

	public List<String> getListaShine() {
		return listaShine;
	}

	public void setListaShine(List<String> listaShine) {
		this.listaShine = listaShine;
	}

	public List<String> getListaShoot() {
		return listaShoot;
	}

	public void setListaShoot(List<String> listaShoot) {
		this.listaShoot = listaShoot;
	}

	public List<String> getListaShow1() {
		return listaShow1;
	}

	public void setListaShow1(List<String> listaShow1) {
		this.listaShow1 = listaShow1;
	}

	public List<String> getListaShow2() {
		return listaShow2;
	}

	public void setListaShow2(List<String> listaShow2) {
		this.listaShow2 = listaShow2;
	}

	public List<String> getListaShut() {
		return listaShut;
	}

	public void setListaShut(List<String> listaShut) {
		this.listaShut = listaShut;
	}

	public List<String> getListaSing() {
		return listaSing;
	}

	public void setListaSing(List<String> listaSing) {
		this.listaSing = listaSing;
	}

	public List<String> getListaSit() {
		return listaSit;
	}

	public void setListaSit(List<String> listaSit) {
		this.listaSit = listaSit;
	}

	public List<String> getListaSleep() {
		return listaSleep;
	}

	public void setListaSleep(List<String> listaSleep) {
		this.listaSleep = listaSleep;
	}

	public List<String> getListaSlide() {
		return listaSlide;
	}

	public void setListaSlide(List<String> listaSlide) {
		this.listaSlide = listaSlide;
	}

	public List<String> getListaSmell() {
		return listaSmell;
	}

	public void setListaSmell(List<String> listaSmell) {
		this.listaSmell = listaSmell;
	}

	public List<String> getListaSow1() {
		return listaSow1;
	}

	public void setListaSow1(List<String> listaSow1) {
		this.listaSow1 = listaSow1;
	}

	public List<String> getListaSow2() {
		return listaSow2;
	}

	public void setListaSow2(List<String> listaSow2) {
		this.listaSow2 = listaSow2;
	}

	public List<String> getListaSpeak1() {
		return listaSpeak1;
	}

	public void setListaSpeak1(List<String> listaSpeak1) {
		this.listaSpeak1 = listaSpeak1;
	}

	public List<String> getListaSpeak2() {
		return listaSpeak2;
	}

	public void setListaSpeak2(List<String> listaSpeak2) {
		this.listaSpeak2 = listaSpeak2;
	}

	public List<String> getListaSpeed() {
		return listaSpeed;
	}

	public void setListaSpeed(List<String> listaSpeed) {
		this.listaSpeed = listaSpeed;
	}

	public List<String> getListaSpell() {
		return listaSpell;
	}

	public void setListaSpell(List<String> listaSpell) {
		this.listaSpell = listaSpell;
	}

	public List<String> getListaSpend() {
		return listaSpend;
	}

	public void setListaSpend(List<String> listaSpend) {
		this.listaSpend = listaSpend;
	}

	public List<String> getListaSpill() {
		return listaSpill;
	}

	public void setListaSpill(List<String> listaSpill) {
		this.listaSpill = listaSpill;
	}

	public List<String> getListaSpit() {
		return listaSpit;
	}

	public void setListaSpit(List<String> listaSpit) {
		this.listaSpit = listaSpit;
	}

	public List<String> getListaSpoil() {
		return listaSpoil;
	}

	public void setListaSpoil(List<String> listaSpoil) {
		this.listaSpoil = listaSpoil;
	}

	public List<String> getListaSpread() {
		return listaSpread;
	}

	public void setListaSpread(List<String> listaSpread) {
		this.listaSpread = listaSpread;
	}

	public List<String> getListaSteal() {
		return listaSteal;
	}

	public void setListaSteal(List<String> listaSteal) {
		this.listaSteal = listaSteal;
	}

	public List<String> getListaSting() {
		return listaSting;
	}

	public void setListaSting(List<String> listaSting) {
		this.listaSting = listaSting;
	}

	public List<String> getListaStink1() {
		return listaStink1;
	}

	public void setListaStink1(List<String> listaStink1) {
		this.listaStink1 = listaStink1;
	}

	public List<String> getListaStink2() {
		return listaStink2;
	}

	public void setListaStink2(List<String> listaStink2) {
		this.listaStink2 = listaStink2;
	}

	public List<String> getListaSwear1() {
		return listaSwear1;
	}

	public void setListaSwear1(List<String> listaSwear1) {
		this.listaSwear1 = listaSwear1;
	}

	public List<String> getListaSwear2() {
		return listaSwear2;
	}

	public void setListaSwear2(List<String> listaSwear2) {
		this.listaSwear2 = listaSwear2;
	}

	public List<String> getListaSweat1() {
		return listaSweat1;
	}

	public void setListaSweat1(List<String> listaSweat1) {
		this.listaSweat1 = listaSweat1;
	}

	public List<String> getListaSweat2() {
		return listaSweat2;
	}

	public void setListaSweat2(List<String> listaSweat2) {
		this.listaSweat2 = listaSweat2;
	}

	public List<String> getListaSweep1() {
		return listaSweep1;
	}

	public void setListaSweep1(List<String> listaSweep1) {
		this.listaSweep1 = listaSweep1;
	}

	public List<String> getListaSweep2() {
		return listaSweep2;
	}

	public void setListaSweep2(List<String> listaSweep2) {
		this.listaSweep2 = listaSweep2;
	}

	public List<String> getListaSwim1() {
		return listaSwim1;
	}

	public void setListaSwim1(List<String> listaSwim1) {
		this.listaSwim1 = listaSwim1;
	}

	public List<String> getListaSwim2() {
		return listaSwim2;
	}

	public void setListaSwim2(List<String> listaSwim2) {
		this.listaSwim2 = listaSwim2;
	}

	public List<String> getListaTake1() {
		return listaTake1;
	}

	public void setListaTake1(List<String> listaTake1) {
		this.listaTake1 = listaTake1;
	}

	public List<String> getListaTake2() {
		return listaTake2;
	}

	public void setListaTake2(List<String> listaTake2) {
		this.listaTake2 = listaTake2;
	}

	public List<String> getListaTeach() {
		return listaTeach;
	}

	public void setListaTeach(List<String> listaTeach) {
		this.listaTeach = listaTeach;
	}

	public List<String> getListaTell() {
		return listaTell;
	}

	public void setListaTell(List<String> listaTell) {
		this.listaTell = listaTell;
	}

	public List<String> getListaThink1() {
		return listaThink1;
	}

	public void setListaThink1(List<String> listaThink1) {
		this.listaThink1 = listaThink1;
	}

	public List<String> getListaThink2() {
		return listaThink2;
	}

	public void setListaThink2(List<String> listaThink2) {
		this.listaThink2 = listaThink2;
	}

	public List<String> getListaThrow1() {
		return listaThrow1;
	}

	public void setListaThrow1(List<String> listaThrow1) {
		this.listaThrow1 = listaThrow1;
	}

	public List<String> getListaThrow2() {
		return listaThrow2;
	}

	public void setListaThrow2(List<String> listaThrow2) {
		this.listaThrow2 = listaThrow2;
	}

	public List<String> getListaUnderstand() {
		return listaUnderstand;
	}

	public void setListaUnderstand(List<String> listaUnderstand) {
		this.listaUnderstand = listaUnderstand;
	}

	public List<String> getListaWake1() {
		return listaWake1;
	}

	public void setListaWake1(List<String> listaWake1) {
		this.listaWake1 = listaWake1;
	}

	public List<String> getListaWake2() {
		return listaWake2;
	}

	public void setListaWake2(List<String> listaWake2) {
		this.listaWake2 = listaWake2;
	}

	public List<String> getListaWake3() {
		return listaWake3;
	}

	public void setListaWake3(List<String> listaWake3) {
		this.listaWake3 = listaWake3;
	}

	public List<String> getListaWake4() {
		return listaWake4;
	}

	public void setListaWake4(List<String> listaWake4) {
		this.listaWake4 = listaWake4;
	}

	public List<String> getListaWake5() {
		return listaWake5;
	}

	public void setListaWake5(List<String> listaWake5) {
		this.listaWake5 = listaWake5;
	}

	public List<String> getListaWear1() {
		return listaWear1;
	}

	public void setListaWear1(List<String> listaWear1) {
		this.listaWear1 = listaWear1;
	}

	public List<String> getListaWear2() {
		return listaWear2;
	}

	public void setListaWear2(List<String> listaWear2) {
		this.listaWear2 = listaWear2;
	}

	public List<String> getListaWeep1() {
		return listaWeep1;
	}

	public void setListaWeep1(List<String> listaWeep1) {
		this.listaWeep1 = listaWeep1;
	}

	public List<String> getListaWeep2() {
		return listaWeep2;
	}

	public void setListaWeep2(List<String> listaWeep2) {
		this.listaWeep2 = listaWeep2;
	}

	public List<String> getListaWet1() {
		return listaWet1;
	}

	public void setListaWet1(List<String> listaWet1) {
		this.listaWet1 = listaWet1;
	}

	public List<String> getListaWet2() {
		return listaWet2;
	}

	public void setListaWet2(List<String> listaWet2) {
		this.listaWet2 = listaWet2;
	}

	public List<String> getListaWin1() {
		return listaWin1;
	}

	public void setListaWin1(List<String> listaWin1) {
		this.listaWin1 = listaWin1;
	}

	public List<String> getListaWin2() {
		return listaWin2;
	}

	public void setListaWin2(List<String> listaWin2) {
		this.listaWin2 = listaWin2;
	}

	public List<String> getListaWrite() {
		return listaWrite;
	}

	public void setListaWrite(List<String> listaWrite) {
		this.listaWrite = listaWrite;
	}
}

