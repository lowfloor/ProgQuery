package database.relations;

public enum RelationTypes implements RelationTypesInterface {

	ARRAYACCESS_EXPR, ARRAYACCESS_INDEX,

	ASSERT_CONDITION, ASSERT_DETAIL, ASSIGNMENT_LHS,

	ASSIGNMENT_RHS, BINOP_LHS,

	BINOP_RHS, BINOP_COND_RHS, CASE_EXPR, CASE_STATEMENTS, CAST_ENCLOSES,

	CAST_TYPE, CATCH_ENCLOSES_BLOCK, CATCH_PARAM, COMPOUND_ASSIGNMENT_LHS,

	COMPOUND_ASSIGNMENT_RHS, CONDITIONAL_EXPR_CONDITION,

	CONDITIONAL_EXPR_ELSE, CONDITIONAL_EXPR_THEN,

	DECLARES_FIELD, DECLARES_METHOD, DECLARES_CONSTRUCTOR, DO_WHILE_CONDITION,

	 ENCLOSES,	WHILE_STATEMENT, DO_WHILE_STATEMENT,  HAS_ENUM_ELEMENT, ERRONEOUS_NODE_CAUSED_BY, ENCLOSES_EXPR,

	FOREACH_EXPR, FOREACH_STATEMENT, FOREACH_VAR,

	FORLOOP_CONDITION, FORLOOP_INIT, FORLOOP_STATEMENT, FORLOOP_UPDATE,

	HAS_ANNOTATIONS, HAS_ANNOTATIONS_ARGUMENTS, HAS_ANNOTATION_TYPE,

	HAS_EXTENDS_CLAUSE, HAS_IMPLEMENTS_CLAUSE, HAS_CLASS_TYPEPARAMETERS

	,CALLABLE_HAS_BODY, HAS_DEFAULT_VALUE, CALLABLE_HAS_PARAMETER,

	CALLABLE_RETURN_TYPE, CALLABLE_HAS_THROWS, CALLABLE_HAS_TYPEPARAMETERS, HAS_RECEIVER_PARAMETER, HAS_STATIC_INIT,

	 HAS_TYPE_DEF, HAS_VARIABLEDECL_INIT,  HAS_VARIABLEDECL_TYPE,

	IF_CONDITION, IF_ELSE, IF_THEN, IMPORTS,

	INSTANCE_OF_EXPR, INSTANCE_OF_TYPE, INTERSECTION_COMPOSED_OF, 

	LABELED_STMT_ENCLOSES, LAMBDA_EXPRESSION_BODY, LAMBDA_EXPRESSION_PARAMETERS, MEMBER_REFERENCE_EXPRESSION, MEMBER_REFERENCE_TYPE_ARGUMENTS, MEMBER_SELECT_EXPR,

	METHODINVOCATION_ARGUMENTS, METHODINVOCATION_METHOD_SELECT, METHODINVOCATION_TYPE_ARGUMENTS, NEW_CLASS_ARGUMENTS, NEW_CLASS_BODY,

	NEW_CLASS_TYPE_ARGUMENTS, NEW_ARRAY_DIMENSION, NEW_ARRAY_INIT, NEW_ARRAY_TYPE, NEWCLASS_ENCLOSING_EXPRESSION, NEWCLASS_IDENTIFIER,

	PARAMETERIZED_TYPE, GENERIC_TYPE_ARGUMENT,  RETURN_EXPR,

	SWITCH_ENCLOSES_CASE, SWITCH_EXPR, SYNCHRONIZED_ENCLOSES_BLOCK, SYNCHRONIZED_EXPR, THROW_EXPR, TRY_BLOCK,

	TRY_CATCH, TRY_FINALLY, TRY_RESOURCES, TYPE_PER_ELEMENT, TYPEPARAMETER_EXTENDS, UNARY_ENCLOSES,

	UNDERLYING_TYPE, UNION_TYPE_ALTERNATIVE, WHILE_CONDITION, WILDCARD_BOUND, INITIALIZATION_EXPR,    LOWER_BOUND_TYPE, UPPER_BOUND_TYPE
	
	
	
	

}
