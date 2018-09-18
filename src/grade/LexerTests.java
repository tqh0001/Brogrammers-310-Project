package grade;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class LexerTests {
	public static spec.Lexer L;
	
	@BeforeClass
	public static void initialize() {
		L = new team.Lexer();
	}
	
	@Parameter(value=0)
	public static String sentence;
	
	@Parameter(value=1)
	public static String tokens;
	
	@Parameters(name="#{index}: {0}")
	public static java.util.Collection<Object[]> data() {
		return java.util.Arrays.asList(new Object[][]{
			{ "QUERY TRUE." , "QUERY TRUE_LITERAL END_QUERY" },
			{ "QUERY FALSE." , "QUERY FALSE_LITERAL END_QUERY" },
			{ "QUERY ~TRUE." , "QUERY NEGATION TRUE_LITERAL END_QUERY" },
			{ "QUERY ~FALSE." , "QUERY NEGATION FALSE_LITERAL END_QUERY" },
			
			{ "QUERY TRUE -> TRUE." , "QUERY TRUE_LITERAL IMPLICATION TRUE_LITERAL END_QUERY" },
			{ "QUERY TRUE -> FALSE." , "QUERY TRUE_LITERAL IMPLICATION FALSE_LITERAL END_QUERY" },
			{ "QUERY FALSE -> TRUE." , "QUERY FALSE_LITERAL IMPLICATION TRUE_LITERAL END_QUERY" },
			{ "QUERY FALSE -> FALSE." , "QUERY FALSE_LITERAL IMPLICATION FALSE_LITERAL END_QUERY" },
			
			{ "QUERY TRUE <=> TRUE." , "QUERY TRUE_LITERAL EQUIVALENCE TRUE_LITERAL END_QUERY" },
			{ "QUERY TRUE <=> FALSE." , "QUERY TRUE_LITERAL EQUIVALENCE FALSE_LITERAL END_QUERY" },
			{ "QUERY FALSE <=> TRUE." , "QUERY FALSE_LITERAL EQUIVALENCE TRUE_LITERAL END_QUERY" },
			{ "QUERY FALSE <=> FALSE." , "QUERY FALSE_LITERAL EQUIVALENCE FALSE_LITERAL END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  QUERY p & q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; QUERY p & q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  QUERY p & q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; QUERY p & q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  LET r = TRUE;   QUERY p & q & r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = TRUE;  LET r = FALSE;  QUERY p & q & r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; LET r = TRUE;   QUERY p & q & r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; LET r = FALSE;  QUERY p & q & r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  LET r = TRUE;   QUERY p & q & r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  LET r = FALSE;  QUERY p & q & r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; LET r = TRUE;   QUERY p & q & r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; LET r = FALSE;  QUERY p & q & r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME CONJUNCTION VARIABLE_NAME CONJUNCTION VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  QUERY p | q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; QUERY p | q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  QUERY p | q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; QUERY p | q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  LET r = TRUE;   QUERY p | q | r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = TRUE;  LET r = FALSE;  QUERY p | q | r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; LET r = TRUE;   QUERY p | q | r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; LET r = FALSE;  QUERY p | q | r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  LET r = TRUE;   QUERY p | q | r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  LET r = FALSE;  QUERY p | q | r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; LET r = TRUE;   QUERY p | q | r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; LET r = FALSE;  QUERY p | q | r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME DISJUNCTION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  QUERY p -> q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; QUERY p -> q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  QUERY p -> q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; QUERY p -> q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  LET r = TRUE;   QUERY p -> q -> r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = TRUE;  LET r = FALSE;  QUERY p -> q -> r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; LET r = TRUE;   QUERY p -> q -> r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; LET r = FALSE;  QUERY p -> q -> r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  LET r = TRUE;   QUERY p -> q -> r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  LET r = FALSE;  QUERY p -> q -> r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; LET r = TRUE;   QUERY p -> q -> r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; LET r = FALSE;  QUERY p -> q -> r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME IMPLICATION VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  QUERY p <=> q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; QUERY p <=> q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  QUERY p <=> q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; QUERY p <=> q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  LET r = TRUE;   QUERY p <=> q <=> r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = TRUE;  LET r = FALSE;  QUERY p <=> q <=> r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; LET r = TRUE;   QUERY p <=> q <=> r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; LET r = FALSE;  QUERY p <=> q <=> r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  LET r = TRUE;   QUERY p <=> q <=> r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  LET r = FALSE;  QUERY p <=> q <=> r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; LET r = TRUE;   QUERY p <=> q <=> r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; LET r = FALSE;  QUERY p <=> q <=> r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME EQUIVALENCE VARIABLE_NAME EQUIVALENCE VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  QUERY p." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; QUERY p." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  QUERY ~p." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY NEGATION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; QUERY ~p." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY NEGATION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  QUERY ~(~p)." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY NEGATION LEFT_PAREN NEGATION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = FALSE; QUERY ~(~p)." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY NEGATION LEFT_PAREN NEGATION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = TRUE;  QUERY (p)." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY LEFT_PAREN VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = FALSE; QUERY (p)." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY LEFT_PAREN VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = TRUE;  QUERY ((p))." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY LEFT_PAREN LEFT_PAREN VARIABLE_NAME RIGHT_PAREN RIGHT_PAREN END_QUERY" },
			{ "LET p = FALSE; QUERY ((p))." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY LEFT_PAREN LEFT_PAREN VARIABLE_NAME RIGHT_PAREN RIGHT_PAREN END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  QUERY ~(p & q) <=> (~p | ~q)." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY NEGATION LEFT_PAREN VARIABLE_NAME CONJUNCTION VARIABLE_NAME RIGHT_PAREN EQUIVALENCE LEFT_PAREN NEGATION VARIABLE_NAME DISJUNCTION NEGATION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; QUERY ~(p & q) <=> (~p | ~q)." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY NEGATION LEFT_PAREN VARIABLE_NAME CONJUNCTION VARIABLE_NAME RIGHT_PAREN EQUIVALENCE LEFT_PAREN NEGATION VARIABLE_NAME DISJUNCTION NEGATION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  QUERY ~(p & q) <=> (~p | ~q)." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY NEGATION LEFT_PAREN VARIABLE_NAME CONJUNCTION VARIABLE_NAME RIGHT_PAREN EQUIVALENCE LEFT_PAREN NEGATION VARIABLE_NAME DISJUNCTION NEGATION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; QUERY ~(p & q) <=> (~p | ~q)." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY NEGATION LEFT_PAREN VARIABLE_NAME CONJUNCTION VARIABLE_NAME RIGHT_PAREN EQUIVALENCE LEFT_PAREN NEGATION VARIABLE_NAME DISJUNCTION NEGATION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  QUERY (p -> q) <=> (~p | q)." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY LEFT_PAREN VARIABLE_NAME IMPLICATION VARIABLE_NAME RIGHT_PAREN EQUIVALENCE LEFT_PAREN NEGATION VARIABLE_NAME DISJUNCTION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; QUERY (p -> q) <=> (~p | q)." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY LEFT_PAREN VARIABLE_NAME IMPLICATION VARIABLE_NAME RIGHT_PAREN EQUIVALENCE LEFT_PAREN NEGATION VARIABLE_NAME DISJUNCTION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  QUERY (p -> q) <=> (~p | q)." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY LEFT_PAREN VARIABLE_NAME IMPLICATION VARIABLE_NAME RIGHT_PAREN EQUIVALENCE LEFT_PAREN NEGATION VARIABLE_NAME DISJUNCTION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; QUERY (p -> q) <=> (~p | q)." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY LEFT_PAREN VARIABLE_NAME IMPLICATION VARIABLE_NAME RIGHT_PAREN EQUIVALENCE LEFT_PAREN NEGATION VARIABLE_NAME DISJUNCTION VARIABLE_NAME RIGHT_PAREN END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  QUERY p -> q <=> ~p | q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME EQUIVALENCE NEGATION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; QUERY p -> q <=> ~p | q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME EQUIVALENCE NEGATION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  QUERY p -> q <=> ~p | q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME EQUIVALENCE NEGATION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; QUERY p -> q <=> ~p | q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME IMPLICATION VARIABLE_NAME EQUIVALENCE NEGATION VARIABLE_NAME DISJUNCTION VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  LET q = p;  QUERY q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN VARIABLE_NAME END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = p;  QUERY q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN VARIABLE_NAME END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = ~p; QUERY q." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN NEGATION VARIABLE_NAME END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = ~p; QUERY q." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN NEGATION VARIABLE_NAME END_LET QUERY VARIABLE_NAME END_QUERY" },
			
			{ "LET p = TRUE;  LET q = TRUE;  LET r = ~p & ~q; QUERY r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN NEGATION VARIABLE_NAME CONJUNCTION NEGATION VARIABLE_NAME END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET p = TRUE;  LET q = FALSE; LET r = ~p & ~q; QUERY r." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN NEGATION VARIABLE_NAME CONJUNCTION NEGATION VARIABLE_NAME END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = TRUE;  LET r = ~p & ~q; QUERY r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET LET VARIABLE_NAME ASSIGN NEGATION VARIABLE_NAME CONJUNCTION NEGATION VARIABLE_NAME END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET p = FALSE; LET q = FALSE; LET r = ~p & ~q; QUERY r." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET LET VARIABLE_NAME ASSIGN NEGATION VARIABLE_NAME CONJUNCTION NEGATION VARIABLE_NAME END_LET QUERY VARIABLE_NAME END_QUERY" },
			
			{ "LET red = TRUE;     QUERY red." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET green = FALSE;  QUERY green." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LET blue = TRUE;    QUERY ~blue." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY NEGATION VARIABLE_NAME END_QUERY" },
			{ "LET yellow = FALSE; QUERY ~yellow." , "LET VARIABLE_NAME ASSIGN FALSE_LITERAL END_LET QUERY NEGATION VARIABLE_NAME END_QUERY" },
			
			{ "let p = true; query p." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "Let P = True; Query P." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "lEt p = tRUe; quEry P." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME END_QUERY" },
			{ "LeT P = TruE; QuerY p." , "LET VARIABLE_NAME ASSIGN TRUE_LITERAL END_LET QUERY VARIABLE_NAME END_QUERY" },

			{ "LET ->->P. Q" , "LET IMPLICATION IMPLICATION VARIABLE_NAME END_QUERY VARIABLE_NAME" },
			{ "&|QUERY LET P ~)", "CONJUNCTION DISJUNCTION QUERY LET VARIABLE_NAME NEGATION RIGHT_PAREN" },
		});
	}
	
	@Test
	public void testProgram() {
		L.initialize(sentence);
		
		StringBuilder T = new StringBuilder();
		L.lex();
		while (L.TOKEN != null) {
			if (T.length() > 0)
				T.append(' ');
			T.append(L.TOKEN.toString());
			L.lex();
		}
		
		assertEquals(
			"Lexical error",
			tokens,
			T.toString()
		);
	}
}