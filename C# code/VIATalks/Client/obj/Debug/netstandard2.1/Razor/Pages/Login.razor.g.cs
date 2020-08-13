#pragma checksum "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\Login.razor" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "73698ce67453dcfc729a8044ca574eb5002fccb2"
// <auto-generated/>
#pragma warning disable 1591
namespace Client.Pages
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Components;
#nullable restore
#line 1 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using System.Net.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using System.Net.Http.Json;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using Microsoft.AspNetCore.Components.Forms;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using Microsoft.AspNetCore.Components.Routing;

#line default
#line hidden
#nullable disable
#nullable restore
#line 5 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using Microsoft.AspNetCore.Components.Web;

#line default
#line hidden
#nullable disable
#nullable restore
#line 6 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using Microsoft.AspNetCore.Components.WebAssembly.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using Microsoft.JSInterop;

#line default
#line hidden
#nullable disable
#nullable restore
#line 8 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using Client;

#line default
#line hidden
#nullable disable
#nullable restore
#line 9 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using Client.Shared;

#line default
#line hidden
#nullable disable
#nullable restore
#line 10 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\_Imports.razor"
using DataClasses;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\Login.razor"
using Client.Security;

#line default
#line hidden
#nullable disable
    [Microsoft.AspNetCore.Components.RouteAttribute("/login")]
    public partial class Login : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
            __builder.AddMarkupContent(0, "<h1>Login</h1>\r\n\r\n");
            __builder.OpenElement(1, "form");
            __builder.AddAttribute(2, "onsubmit", Microsoft.AspNetCore.Components.EventCallback.Factory.Create<System.EventArgs>(this, 
#nullable restore
#line 9 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\Login.razor"
                 () => LoginAsync()

#line default
#line hidden
#nullable disable
            ));
            __builder.AddMarkupContent(3, "\r\n\r\n    ");
            __builder.AddMarkupContent(4, "<h2>Input your credentials:</h2>\r\n\r\n    ");
            __builder.AddMarkupContent(5, "<label>Username:</label>\r\n    ");
            __builder.OpenElement(6, "input");
            __builder.AddAttribute(7, "type", "text");
            __builder.AddAttribute(8, "value", Microsoft.AspNetCore.Components.BindConverter.FormatValue(
#nullable restore
#line 14 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\Login.razor"
                                    Username

#line default
#line hidden
#nullable disable
            ));
            __builder.AddAttribute(9, "onchange", Microsoft.AspNetCore.Components.EventCallback.Factory.CreateBinder(this, __value => Username = __value, Username));
            __builder.SetUpdatesAttributeName("value");
            __builder.CloseElement();
            __builder.AddMarkupContent(10, "\r\n    <br>\r\n    ");
            __builder.AddMarkupContent(11, "<label>Password:</label>\r\n    ");
            __builder.OpenElement(12, "input");
            __builder.AddAttribute(13, "type", "text");
            __builder.AddAttribute(14, "value", Microsoft.AspNetCore.Components.BindConverter.FormatValue(
#nullable restore
#line 17 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\Login.razor"
                                    Password

#line default
#line hidden
#nullable disable
            ));
            __builder.AddAttribute(15, "onchange", Microsoft.AspNetCore.Components.EventCallback.Factory.CreateBinder(this, __value => Password = __value, Password));
            __builder.SetUpdatesAttributeName("value");
            __builder.CloseElement();
            __builder.AddMarkupContent(16, "\r\n    <br>\r\n\r\n    <input type=\"submit\" value=\"Login\">\r\n\r\n    ");
            __builder.OpenElement(17, "p");
            __builder.AddAttribute(18, "style", "color:red");
            __builder.AddContent(19, 
#nullable restore
#line 22 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\Login.razor"
                          ErrorMessage

#line default
#line hidden
#nullable disable
            );
            __builder.CloseElement();
            __builder.AddMarkupContent(20, "\r\n");
            __builder.CloseElement();
        }
        #pragma warning restore 1998
#nullable restore
#line 25 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\Login.razor"
       
    private string Username { get; set; }
    private string Password { get; set; }
    private string ErrorMessage { get; set; } = "";

    public async Task LoginAsync()
    {
        if (Username != null || Username == "")
        {
            ErrorMessage = "You must input username";
            Password = "";
            return;
        }

        if(Password != null || Password == "")
        {
            ErrorMessage = "You must input password";
            Password = "";
            return;
        }

        Console.WriteLine("Login called");

        string hashedPass = UserManager.HashPassword(Password);

        List<bool> signedIn = await Http.GetFromJsonAsync<List<bool>>($"user/login?username={Username}&password={hashedPass}");

        if (!signedIn[0])
        {
            ErrorMessage = "Could not log in";
            Password = "";
            return;
        }

        User.LoggedIn = true;
        if(signedIn[1] == true)
        {
            User.Access = UserManager.UserPrivilege.Admin;
        } else
        {
            User.Access = UserManager.UserPrivilege.User;
        }

        UriHelper.NavigateTo("schedule");
    }

#line default
#line hidden
#nullable disable
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private UserManager User { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private NavigationManager UriHelper { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private HttpClient Http { get; set; }
    }
}
#pragma warning restore 1591
