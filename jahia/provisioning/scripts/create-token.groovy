import java.time.LocalDate
import java.util.Calendar

org.jahia.services.content.JCRTemplate.getInstance().doExecuteWithSystemSession({ session ->
    LocalDate months = LocalDate.now().plusMonths(3)
    List<String> scopes = ["graphql", "community-mcp"]
    org.jahia.osgi.BundleUtils.getOsgiService("org.jahia.modules.apitokens.TokenService")
        .tokenBuilder("/users/root", "my-mcp-token", session)
        .setToken(System.getenv("MCP_API_TOKEN")).setScopes(scopes)
        .setActive(true)
        .setExpirationDate(toCalendar(months))
        .create()
    session.save();
})

Calendar toCalendar(LocalDate localDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
    return calendar;
}