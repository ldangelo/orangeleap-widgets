SET TRANSACTION;


update WIDGET
SET AUTHENTICATION_REQUIRED = 1
WHERE (WIDGET_TYPE IN ('gifthistory', 'pledges') OR CUSTOM_ENTITY_NAME IN ('donor_profile'));

COMMIT;