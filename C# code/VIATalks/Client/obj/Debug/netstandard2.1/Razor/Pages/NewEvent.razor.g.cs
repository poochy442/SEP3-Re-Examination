#pragma checksum "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "1d379603391edafb492cfc6c306bb78f90605fa4"
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
#line 2 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
using Client.Model;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
using System.Net.Http;

#line default
#line hidden
#nullable disable
    [Microsoft.AspNetCore.Components.RouteAttribute("/new_event")]
    public partial class NewEvent : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
            __builder.AddMarkupContent(0, "<h1>New Event</h1>\r\n\r\n");
            __builder.OpenComponent<Microsoft.AspNetCore.Components.Forms.EditForm>(1);
            __builder.AddAttribute(2, "Model", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.Object>(
#nullable restore
#line 9 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                  eventModel

#line default
#line hidden
#nullable disable
            ));
            __builder.AddAttribute(3, "OnValidSubmit", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<Microsoft.AspNetCore.Components.EventCallback<Microsoft.AspNetCore.Components.Forms.EditContext>>(Microsoft.AspNetCore.Components.EventCallback.Factory.Create<Microsoft.AspNetCore.Components.Forms.EditContext>(this, 
#nullable restore
#line 9 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                             HandleValidSubmit

#line default
#line hidden
#nullable disable
            )));
            __builder.AddAttribute(4, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment<Microsoft.AspNetCore.Components.Forms.EditContext>)((context) => (__builder2) => {
                __builder2.AddMarkupContent(5, "\r\n    ");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Forms.DataAnnotationsValidator>(6);
                __builder2.CloseComponent();
                __builder2.AddMarkupContent(7, "\r\n    ");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Forms.ValidationSummary>(8);
                __builder2.AddAttribute(9, "Model", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.Object>(
#nullable restore
#line 11 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                               eventModel

#line default
#line hidden
#nullable disable
                ));
                __builder2.CloseComponent();
                __builder2.AddMarkupContent(10, "\r\n\r\n    ");
                __builder2.OpenElement(11, "p");
                __builder2.AddMarkupContent(12, "\r\n        ");
                __builder2.OpenElement(13, "label");
                __builder2.AddMarkupContent(14, "\r\n            Event Name:\r\n            ");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Forms.InputText>(15);
                __builder2.AddAttribute(16, "id", "eventName");
                __builder2.AddAttribute(17, "Value", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.String>(
#nullable restore
#line 16 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                                   eventModel.EventName

#line default
#line hidden
#nullable disable
                ));
                __builder2.AddAttribute(18, "ValueChanged", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<Microsoft.AspNetCore.Components.EventCallback<System.String>>(Microsoft.AspNetCore.Components.EventCallback.Factory.Create<System.String>(this, Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.CreateInferredEventCallback(this, __value => eventModel.EventName = __value, eventModel.EventName))));
                __builder2.AddAttribute(19, "ValueExpression", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.Linq.Expressions.Expression<System.Func<System.String>>>(() => eventModel.EventName));
                __builder2.CloseComponent();
                __builder2.AddMarkupContent(20, "\r\n        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(21, "\r\n    ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(22, "\r\n    ");
                __builder2.OpenElement(23, "p");
                __builder2.AddMarkupContent(24, "\r\n        ");
                __builder2.OpenElement(25, "label");
                __builder2.AddMarkupContent(26, "\r\n            Event Category:\r\n            ");
                __Blazor.Client.Pages.NewEvent.TypeInference.CreateInputSelect_0(__builder2, 27, 28, "category", 29, 
#nullable restore
#line 22 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                                    eventModel.Category

#line default
#line hidden
#nullable disable
                , 30, Microsoft.AspNetCore.Components.EventCallback.Factory.Create(this, Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.CreateInferredEventCallback(this, __value => eventModel.Category = __value, eventModel.Category)), 31, () => eventModel.Category, 32, (__builder3) => {
                    __builder3.AddMarkupContent(33, "\r\n                ");
                    __builder3.AddMarkupContent(34, "<option value>Select Category</option>\r\n                ");
                    __builder3.OpenElement(35, "option");
                    __builder3.AddAttribute(36, "value", "Educational");
                    __builder3.AddContent(37, 
#nullable restore
#line 24 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                             Event.CATEGORIES.Educational

#line default
#line hidden
#nullable disable
                    );
                    __builder3.CloseElement();
                    __builder3.AddMarkupContent(38, "\r\n                ");
                    __builder3.OpenElement(39, "option");
                    __builder3.AddAttribute(40, "value", "Entertainment");
                    __builder3.AddContent(41, 
#nullable restore
#line 25 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                               Event.CATEGORIES.Entertainment

#line default
#line hidden
#nullable disable
                    );
                    __builder3.CloseElement();
                    __builder3.AddMarkupContent(42, "\r\n            ");
                }
                );
                __builder2.AddMarkupContent(43, "\r\n        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(44, "\r\n    ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(45, "\r\n    ");
                __builder2.OpenElement(46, "p");
                __builder2.AddMarkupContent(47, "\r\n        ");
                __builder2.OpenElement(48, "label");
                __builder2.AddMarkupContent(49, "\r\n            Maximum Accomidation:\r\n            ");
                __Blazor.Client.Pages.NewEvent.TypeInference.CreateInputNumber_1(__builder2, 50, 51, "accomidation", 52, 
#nullable restore
#line 32 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                                        eventModel.NumberOfSeats

#line default
#line hidden
#nullable disable
                , 53, Microsoft.AspNetCore.Components.EventCallback.Factory.Create(this, Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.CreateInferredEventCallback(this, __value => eventModel.NumberOfSeats = __value, eventModel.NumberOfSeats)), 54, () => eventModel.NumberOfSeats);
                __builder2.AddMarkupContent(55, "\r\n        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(56, "\r\n    ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(57, "\r\n    ");
                __builder2.OpenElement(58, "p");
                __builder2.AddMarkupContent(59, "\r\n        ");
                __builder2.OpenElement(60, "label");
                __builder2.AddMarkupContent(61, "\r\n            Start time:\r\n            ");
                __Blazor.Client.Pages.NewEvent.TypeInference.CreateInputDate_2(__builder2, 62, 63, "startTime", 64, 
#nullable restore
#line 38 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                                   eventModel.StartTime

#line default
#line hidden
#nullable disable
                , 65, Microsoft.AspNetCore.Components.EventCallback.Factory.Create(this, Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.CreateInferredEventCallback(this, __value => eventModel.StartTime = __value, eventModel.StartTime)), 66, () => eventModel.StartTime);
                __builder2.AddMarkupContent(67, "\r\n        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(68, "\r\n    ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(69, "\r\n    ");
                __builder2.OpenElement(70, "p");
                __builder2.AddMarkupContent(71, "\r\n        ");
                __builder2.OpenElement(72, "label");
                __builder2.AddMarkupContent(73, "\r\n            End time:\r\n            ");
                __Blazor.Client.Pages.NewEvent.TypeInference.CreateInputDate_3(__builder2, 74, 75, "endTime", 76, 
#nullable restore
#line 44 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                                 eventModel.EndTime

#line default
#line hidden
#nullable disable
                , 77, Microsoft.AspNetCore.Components.EventCallback.Factory.Create(this, Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.CreateInferredEventCallback(this, __value => eventModel.EndTime = __value, eventModel.EndTime)), 78, () => eventModel.EndTime);
                __builder2.AddMarkupContent(79, "\r\n        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(80, "\r\n    ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(81, "\r\n\r\n    \r\n    ");
                __builder2.AddMarkupContent(82, "<h2>Room:</h2>\r\n");
#nullable restore
#line 50 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
     if (Rooms.Count > 0)
    {

#line default
#line hidden
#nullable disable
                __builder2.AddContent(83, "        ");
                __builder2.OpenElement(84, "table");
                __builder2.AddMarkupContent(85, "\r\n            ");
                __builder2.AddMarkupContent(86, @"<thead>
                <tr>
                    <th>Room Number</th>
                    <th>Block</th>
                    <th>Capacity</th>
                    <th>Area</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            ");
                __builder2.OpenElement(87, "tbody");
                __builder2.AddMarkupContent(88, "\r\n");
#nullable restore
#line 64 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                 foreach (Room r in Rooms)
                {
                    

#line default
#line hidden
#nullable disable
#nullable restore
#line 66 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                     if (r.Equals(eventModel.Room))
                    {

#line default
#line hidden
#nullable disable
                __builder2.AddContent(89, "                        ");
                __builder2.OpenElement(90, "tr");
                __builder2.AddAttribute(91, "style", "background-color:green");
                __builder2.AddMarkupContent(92, "\r\n                            ");
                __builder2.OpenElement(93, "td");
                __builder2.AddContent(94, 
#nullable restore
#line 69 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 r.RoomNumber

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(95, "\r\n                            ");
                __builder2.OpenElement(96, "td");
                __builder2.AddContent(97, 
#nullable restore
#line 70 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 r.Block

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(98, "\r\n                            ");
                __builder2.OpenElement(99, "td");
                __builder2.AddContent(100, 
#nullable restore
#line 71 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 r.Capacity

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(101, "\r\n                            ");
                __builder2.OpenElement(102, "td");
                __builder2.AddContent(103, 
#nullable restore
#line 72 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 r.Area

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(104, "\r\n                            ");
                __builder2.OpenElement(105, "td");
                __builder2.OpenElement(106, "a");
                __builder2.AddAttribute(107, "href", "edit_room/" + (
#nullable restore
#line 73 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                                    r.Id

#line default
#line hidden
#nullable disable
                ));
                __builder2.AddContent(108, "Edit");
                __builder2.CloseElement();
                __builder2.CloseElement();
                __builder2.AddMarkupContent(109, "\r\n                            <td></td>\r\n                        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(110, "\r\n");
#nullable restore
#line 76 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                    }
                    else
                    {

#line default
#line hidden
#nullable disable
                __builder2.AddContent(111, "                        ");
                __builder2.OpenElement(112, "tr");
                __builder2.AddMarkupContent(113, "\r\n                            ");
                __builder2.OpenElement(114, "td");
                __builder2.AddContent(115, 
#nullable restore
#line 80 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 r.RoomNumber

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(116, "\r\n                            ");
                __builder2.OpenElement(117, "td");
                __builder2.AddContent(118, 
#nullable restore
#line 81 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 r.Block

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(119, "\r\n                            ");
                __builder2.OpenElement(120, "td");
                __builder2.AddContent(121, 
#nullable restore
#line 82 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 r.Capacity

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(122, "\r\n                            ");
                __builder2.OpenElement(123, "td");
                __builder2.AddContent(124, 
#nullable restore
#line 83 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 r.Area

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(125, "\r\n                            ");
                __builder2.OpenElement(126, "td");
                __builder2.OpenElement(127, "a");
                __builder2.AddAttribute(128, "href", "edit_room/" + (
#nullable restore
#line 84 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                                    r.Id

#line default
#line hidden
#nullable disable
                ));
                __builder2.AddContent(129, "Edit");
                __builder2.CloseElement();
                __builder2.CloseElement();
                __builder2.AddMarkupContent(130, "\r\n                            ");
                __builder2.OpenElement(131, "td");
                __builder2.OpenElement(132, "button");
                __builder2.AddAttribute(133, "onclick", Microsoft.AspNetCore.Components.EventCallback.Factory.Create<Microsoft.AspNetCore.Components.Web.MouseEventArgs>(this, 
#nullable restore
#line 85 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                                    e => eventModel.Room = r

#line default
#line hidden
#nullable disable
                ));
                __builder2.AddContent(134, "Select");
                __builder2.CloseElement();
                __builder2.CloseElement();
                __builder2.AddMarkupContent(135, "\r\n                        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(136, "\r\n");
#nullable restore
#line 87 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                    }

#line default
#line hidden
#nullable disable
#nullable restore
#line 87 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                     
                }

#line default
#line hidden
#nullable disable
                __builder2.AddContent(137, "            ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(138, "\r\n        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(139, "\r\n");
#nullable restore
#line 91 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
    }
    else
    {

#line default
#line hidden
#nullable disable
                __builder2.AddContent(140, "        ");
                __builder2.AddMarkupContent(141, "<p>Loading rooms...</p>\r\n");
#nullable restore
#line 95 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
    }

#line default
#line hidden
#nullable disable
                __builder2.AddMarkupContent(142, "\r\n    \r\n    ");
                __builder2.AddMarkupContent(143, "<h2>Campus</h2>\r\n");
#nullable restore
#line 99 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
     if (Campuses.Count > 0)
    {

#line default
#line hidden
#nullable disable
                __builder2.AddContent(144, "        ");
                __builder2.OpenElement(145, "table");
                __builder2.AddMarkupContent(146, "\r\n            ");
                __builder2.AddMarkupContent(147, "<thead>\r\n                <tr>\r\n                    <th>City</th>\r\n                    <th>Postal Code</th>\r\n                    <th>Address</th>\r\n                    <th></th>\r\n                </tr>\r\n            </thead>\r\n            ");
                __builder2.OpenElement(148, "tbody");
                __builder2.AddMarkupContent(149, "\r\n");
#nullable restore
#line 111 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                 foreach (Campus c in Campuses)
                {
                    

#line default
#line hidden
#nullable disable
#nullable restore
#line 113 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                     if (c.Equals(eventModel.Campus))
                    {

#line default
#line hidden
#nullable disable
                __builder2.AddContent(150, "                        ");
                __builder2.OpenElement(151, "tr");
                __builder2.AddAttribute(152, "style", "background-color:green");
                __builder2.AddMarkupContent(153, "\r\n                            ");
                __builder2.OpenElement(154, "td");
                __builder2.AddContent(155, 
#nullable restore
#line 116 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 c.City

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(156, "\r\n                            ");
                __builder2.OpenElement(157, "td");
                __builder2.AddContent(158, 
#nullable restore
#line 117 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 c.PostalCode

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(159, "\r\n                            ");
                __builder2.OpenElement(160, "td");
                __builder2.AddContent(161, 
#nullable restore
#line 118 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 c.Address

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(162, "\r\n                            <td></td>\r\n                        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(163, "\r\n");
#nullable restore
#line 121 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                    }
                    else
                    {

#line default
#line hidden
#nullable disable
                __builder2.AddContent(164, "                        ");
                __builder2.OpenElement(165, "tr");
                __builder2.AddMarkupContent(166, "\r\n                            ");
                __builder2.OpenElement(167, "td");
                __builder2.AddContent(168, 
#nullable restore
#line 125 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 c.City

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(169, "\r\n                            ");
                __builder2.OpenElement(170, "td");
                __builder2.AddContent(171, 
#nullable restore
#line 126 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 c.PostalCode

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(172, "\r\n                            ");
                __builder2.OpenElement(173, "td");
                __builder2.AddContent(174, 
#nullable restore
#line 127 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                 c.Address

#line default
#line hidden
#nullable disable
                );
                __builder2.CloseElement();
                __builder2.AddMarkupContent(175, "\r\n                            ");
                __builder2.OpenElement(176, "td");
                __builder2.OpenElement(177, "button");
                __builder2.AddAttribute(178, "onclick", Microsoft.AspNetCore.Components.EventCallback.Factory.Create<Microsoft.AspNetCore.Components.Web.MouseEventArgs>(this, 
#nullable restore
#line 128 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                                                    e => eventModel.Campus = c

#line default
#line hidden
#nullable disable
                ));
                __builder2.AddContent(179, "Select");
                __builder2.CloseElement();
                __builder2.CloseElement();
                __builder2.AddMarkupContent(180, "\r\n                        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(181, "\r\n");
#nullable restore
#line 130 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                    }

#line default
#line hidden
#nullable disable
#nullable restore
#line 130 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
                     

                }

#line default
#line hidden
#nullable disable
                __builder2.AddContent(182, "            ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(183, "\r\n        ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(184, "\r\n");
#nullable restore
#line 135 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
    }
    else
    {

#line default
#line hidden
#nullable disable
                __builder2.AddContent(185, "        ");
                __builder2.AddMarkupContent(186, "<p>Loading campuses...</p>\r\n");
#nullable restore
#line 139 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
    }

#line default
#line hidden
#nullable disable
                __builder2.AddMarkupContent(187, "\r\n    ");
                __builder2.AddMarkupContent(188, "<button type=\"submit\">Submit</button>\r\n\r\n    <p id=\"errorMessage\"></p>\r\n");
            }
            ));
            __builder.CloseComponent();
        }
        #pragma warning restore 1998
#nullable restore
#line 146 "C:\Users\Bruger\Documents\GitHub\SEP3_Re\C# code\VIATalks\Client\Pages\NewEvent.razor"
       
    private EventModel eventModel = new EventModel();
    private EditContext editContext;
    private List<Room> Rooms { get; set; } = new List<Room>();
    private List<Campus> Campuses { get; set; } = new List<Campus>();

    protected override async Task OnInitializedAsync()
    {
        editContext = new EditContext(eventModel);
        eventModel.Host = new Event.EventHost(
            "Kenneth",
            "Jensen",
            "123@abc.com",
            "12345678");

        await GetRooms();
        await GetCampuses();
    }

    private async Task HandleValidSubmit()
    {
        var isValid = editContext.Validate();

        if (isValid)
        {
            Console.WriteLine("IsValid\n");
            HttpResponseMessage rm = await Http.PostAsJsonAsync<Event>("event", eventModel.GetEvent());
            try
            {
                rm.EnsureSuccessStatusCode();
                UriHelper.NavigateTo("schedule");
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine(e.Message + "\n" + e.StackTrace);
                // Display error
            }
        }
        else
        {
            Console.WriteLine("IsNotValid");
        }
    }

    private async Task GetRooms()
    {
        Console.WriteLine("GetRooms called");
        Rooms = await Http.GetFromJsonAsync<List<Room>>("room");
    }

    private async Task GetCampuses()
    {
        Console.WriteLine("GetCampuses called");
        Campuses = await Http.GetFromJsonAsync<List<Campus>>("campus");
    }

#line default
#line hidden
#nullable disable
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private NavigationManager UriHelper { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private HttpClient Http { get; set; }
    }
}
namespace __Blazor.Client.Pages.NewEvent
{
    #line hidden
    internal static class TypeInference
    {
        public static void CreateInputSelect_0<TValue>(global::Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder, int seq, int __seq0, System.Object __arg0, int __seq1, TValue __arg1, int __seq2, global::Microsoft.AspNetCore.Components.EventCallback<TValue> __arg2, int __seq3, global::System.Linq.Expressions.Expression<global::System.Func<TValue>> __arg3, int __seq4, global::Microsoft.AspNetCore.Components.RenderFragment __arg4)
        {
        __builder.OpenComponent<global::Microsoft.AspNetCore.Components.Forms.InputSelect<TValue>>(seq);
        __builder.AddAttribute(__seq0, "id", __arg0);
        __builder.AddAttribute(__seq1, "Value", __arg1);
        __builder.AddAttribute(__seq2, "ValueChanged", __arg2);
        __builder.AddAttribute(__seq3, "ValueExpression", __arg3);
        __builder.AddAttribute(__seq4, "ChildContent", __arg4);
        __builder.CloseComponent();
        }
        public static void CreateInputNumber_1<TValue>(global::Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder, int seq, int __seq0, System.Object __arg0, int __seq1, TValue __arg1, int __seq2, global::Microsoft.AspNetCore.Components.EventCallback<TValue> __arg2, int __seq3, global::System.Linq.Expressions.Expression<global::System.Func<TValue>> __arg3)
        {
        __builder.OpenComponent<global::Microsoft.AspNetCore.Components.Forms.InputNumber<TValue>>(seq);
        __builder.AddAttribute(__seq0, "id", __arg0);
        __builder.AddAttribute(__seq1, "Value", __arg1);
        __builder.AddAttribute(__seq2, "ValueChanged", __arg2);
        __builder.AddAttribute(__seq3, "ValueExpression", __arg3);
        __builder.CloseComponent();
        }
        public static void CreateInputDate_2<TValue>(global::Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder, int seq, int __seq0, System.Object __arg0, int __seq1, TValue __arg1, int __seq2, global::Microsoft.AspNetCore.Components.EventCallback<TValue> __arg2, int __seq3, global::System.Linq.Expressions.Expression<global::System.Func<TValue>> __arg3)
        {
        __builder.OpenComponent<global::Microsoft.AspNetCore.Components.Forms.InputDate<TValue>>(seq);
        __builder.AddAttribute(__seq0, "id", __arg0);
        __builder.AddAttribute(__seq1, "Value", __arg1);
        __builder.AddAttribute(__seq2, "ValueChanged", __arg2);
        __builder.AddAttribute(__seq3, "ValueExpression", __arg3);
        __builder.CloseComponent();
        }
        public static void CreateInputDate_3<TValue>(global::Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder, int seq, int __seq0, System.Object __arg0, int __seq1, TValue __arg1, int __seq2, global::Microsoft.AspNetCore.Components.EventCallback<TValue> __arg2, int __seq3, global::System.Linq.Expressions.Expression<global::System.Func<TValue>> __arg3)
        {
        __builder.OpenComponent<global::Microsoft.AspNetCore.Components.Forms.InputDate<TValue>>(seq);
        __builder.AddAttribute(__seq0, "id", __arg0);
        __builder.AddAttribute(__seq1, "Value", __arg1);
        __builder.AddAttribute(__seq2, "ValueChanged", __arg2);
        __builder.AddAttribute(__seq3, "ValueExpression", __arg3);
        __builder.CloseComponent();
        }
    }
}
#pragma warning restore 1591
